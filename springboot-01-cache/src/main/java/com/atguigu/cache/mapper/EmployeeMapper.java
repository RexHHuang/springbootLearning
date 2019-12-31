package com.atguigu.cache.mapper;

import com.atguigu.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName}, email=#{email}, gender=#{gender}, d_id=#{dId} WHERE id = #{id}")
    public void updateEmp(Employee employee);

    @Delete("delete FROM employee where id = #{id}")
    public void deleteEmpById(Integer id);

    @Insert("insert into employee(lastName, email, gender, d_id) VALUES (#{lastName}, #{email}, #{gender}, #{dId})")
    public void insertEmp(Employee employee);

    @Select("select * from employee where lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
