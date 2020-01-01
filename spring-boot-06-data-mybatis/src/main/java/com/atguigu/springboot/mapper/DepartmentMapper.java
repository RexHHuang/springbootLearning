package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

//指定这是一个操作数据库的mapper，
// 可以配置以一个@MapperScan 在MybatisConfig类上，或者SpringBoot06MybatisApplication类上
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id = #{id}")
    public Department getDeptById(Integer id);

    @Delete("delete form department where id = #{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(departmentName) values (#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName = #{departmentName} where id = #{id}")
    public int updateDept(Department department);
}
