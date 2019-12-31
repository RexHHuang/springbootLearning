package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

    @Autowired
    RedisTemplate redisTemplate; //操作k-v都是对象的

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void contextLoads() {

        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

    /**
     * Redis的常见5大数据类型
     *  String、List、Set、Hash（散列）、ZSet（有序集合）
     *  stringRedisTemplate.opsForValue();【简化操作字符串的】
     *  stringRedisTemplate.opsForList()【列表】
     *  stringRedisTemplate.opsForSet()【集合】
     */
    @Test
    public void test01(){
        //给redis中保存数据
//        stringRedisTemplate.opsForValue().append("msg", "hello");
//        String msg = stringRedisTemplate.opsForValue().get("msg");
//        System.out.println(msg);

//        stringRedisTemplate.opsForList().leftPush("mylist", "1");
//        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    //测试在redis中保存对象
    @Test
    public void test02(){
        Employee emp = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后的数据保存到redis中
//        redisTemplate.opsForValue().set("emp-01", emp);
        //1、将数据已json的格式保存
        //(1)、自己将对象转换为json
        //(2)、redisTemplate默认的序列化规则，改变默认的序列化规则
        empRedisTemplate.opsForValue().set("emp-01", emp);
    }

}
