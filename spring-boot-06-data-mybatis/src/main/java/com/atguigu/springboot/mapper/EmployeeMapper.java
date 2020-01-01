package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.Employee;

//@Mapper或者@MapperScan的方式将接口扫描装配到容器中
public interface EmployeeMapper {

    public Employee getEmployeeById(Integer id);

    public void insertEmp(Employee employee);
}
