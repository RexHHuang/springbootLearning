package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//@CacheConfig(cacheNames = "emp")  这里可以来指定统一的缓存配置(公共配置)，如这个缓存的名字
/*@CacheConfig(cacheManager = "empCacheManager")*/ //用了@Primary指定默认的CacheManager，这里可以不指定
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接冲缓存中获取，不用在去查数据库
     *
     * CacheManager管理多个Cache组件的，对缓存的真正操作在Cache组件中，每一个缓存组件有自己唯一一个名字
     * Cacheable 的几个属性：
     *      cacheNames/value：指定缓存的名字，将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存
     *      key：缓存数据时使用的key，可以用它来指定，默认是使用方法参数的值，
     *          还可以用SpEL表达式取参数的值:#id；参数id的值 等同于#a0,#p0,#root.args[0]
     *      keyGenerator:key的生成器；可以自己指定key的生成器的组件id
     *          key/keyGenerator，二选一使用
     *      cacheManager：指定缓存管理器，或者cacheRsolver指定缓存解析器，也是二选一即可
     *      condition：指定符合条件的情况下才缓存，如：conditon="#id>0"
     *          condition = "#a0 > 1"：第一个参数的值大于一的时候才进行缓存
     *      unless:当unless指定的条件为true，方法的返回值就不被缓存：
     *              即：除非满足此条件我才不缓存否则就缓存，可以获取结果进行判断
     *              unless = "#result == null"，返回的结果为null时就不缓存
     *      sync：是否使用异步模式
     *
     * 原理：
     *      1、自动配置类:CacheAutoConfiguration
     *      2、缓存的配置类：
     *          org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *          org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *      3、那个缓存配置类默认生效？SimpleCacheConfiguration
     *          给容器中注册了一个 ConcurrentMapCacheManager
     *      4、ConcurrentMapCacheManager：管理缓存的，可以获取和创建 ConcurrentMapCache 类型的缓存组件，并把缓存组件放在cacheMap中
     *      5、ConcurrentMapCache：作用是将数据保存在名为store的ConcurrentMap中
     * 运行流程：
     *      1、方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取
     *          （CacheManager先获取相应的缓存），第一次获取缓存如果没有cache组件会自动创建出来
     *      2、去cache组件找那个查找缓存的内容，使用一个key，默认是方法的参数
     *          key是按照某种策略生成出来的，默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
     *          生成key的策略：
     *              如果没有参数，key=new SimpleKey();
     *              如果有一个参数，key=参数的值
     *              如果有多个参数：key=new SimpleKey(params);
     *      3、没有查到缓存就调用目标方法
     *      4、将目标方法的返回结果放进缓存（map）中
     *
     * 注解@Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     * 如果没有运行目标方法并将结果放入缓存，以后再来调用就可以直接使用缓存中的数据
     *
     * 核心：
     *      1）、使用CacheManager【ConcurrentMapCacheManager】按照名字得到Cache【ConcurrentMapCache】组件
     *      2）、key使用keyGenerator生成的，默认是SimpleKeyGenerator
     *
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp" /* keyGenerator = "myKeyGenerator", condition = "#a0 > 0"*/)
    public Employee getEmp(Integer id){
        System.out.println("查询" + id + "号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * -@CachePut：即调用方法，又更新缓存数据，同步更新缓存
     * 修改了数据库的某个数据，同时更新缓存
     *
     * 运行时机：
     *      1、先调用目标方法
     *      2、将目标方法的结果缓存起来
     * 测试步骤：
     *      1、查询1号员工；查到的结果会放在缓存中
     *          key:1 value:lastName-张三
     *      2、以后查询还是之前的结果
     *      3、更新1号员工；【lastName：zhangsan；gender：0】
     *          key:传入的employee对象 值：返回的employee对象
     *      4、查询1号员工？
     *          应该是更新后的员工，然而却是更新前的
     *          why：设置key，让缓存【map】中的key对应的value覆盖为更新后的值
     *          key="#employee.id":使用传入的参数的员工id
     *          key="result.id":使用返回后的id
     *
     */
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp: " + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * -@CacheEvict:缓存清除
     * key:指定要清除的数据
     * allEntries = true，删除缓存中的所有数据
     * beforeInvocation = false，缓存的清除是否在方法之前执行
     *      false：默认，代表在方法执行后执行清除，如果方法出错，则就不会清空缓存了
     *      true：方法之前清空缓存，不管方法有没有出错
     */
    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp: " + id);
//        employeeMapper.deleteEmpById(id);
    }

    /**
     * -@Caching 定义复杂缓存规则
     * 如果再次用lastName查询，则还是要查数据库，这是因为@CachePut这个注解必须先走数据库灾缓存
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {@Cacheable(value = "emp", key = "#lastName")},
            put = {@CachePut(value = "emp", key = "#result.id"), @CachePut(value = "emp", key = "#result.email")}
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
