package com.atguigu.cache.service;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 缓存的数据能存入redis
     * 第二次从缓存中查询就不能反序列化回来
     * 存的的是dept的json数据，CacheManager默认使用RedisTemplate<Object, Employee> empRedisTemplate操作redis
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "dept", cacheManager = "deptCacheManager")
//    public Department getDept(Integer id){
//        System.out.println("查询部门" + id);
//        Department dept = departmentMapper.getDeptById(id);
//        return dept;
//    }

    @Qualifier(value = "deptCacheManager")
    @Autowired
    CacheManager deptCacheManager;

    // 第二种方式实现缓存，直接获取缓存管理器，调用缓存
    public Department getDept(Integer id){
        System.out.println("查询部门" + id);
        Department dept = departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache cache = deptCacheManager.getCache("dept");
        cache.put("dept:" + id, dept);

        return dept;
    }
}
