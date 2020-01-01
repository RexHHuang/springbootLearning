package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.Department;
import com.atguigu.springboot.bean.Employee;
import com.atguigu.springboot.mapper.DepartmentMapper;
import com.atguigu.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @ResponseBody
    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeMapper.getEmployeeById(id);
    }

    @ResponseBody
    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id){
        return departmentMapper.getDeptById(id);
    }

    @ResponseBody
    @GetMapping("/dept")
    public Department insertDept(Department department){
        departmentMapper.insertDept(department);
        return department;
    }
}
