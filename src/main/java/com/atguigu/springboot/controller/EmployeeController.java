package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    //查询所有员工页面
    @GetMapping("/emps")
    public String emps(Model model, Map map, ModelMap mp){
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中 model map  modelMap
        model.addAttribute("emps",employees);
        //thymeleaf默认会拼串
        return "emp/list";
    }

    //添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //thymeleaf默认会拼串
        return "emp/add";
    }

    //员工添加
    //SpringMvc自动将请求参数和入参对象的属性进行一一绑定；要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String save(Employee employee){
        System.out.println("保存的员工信息："+employee);
        employeeDao.save(employee);
        //redirect:表示重定向到一个地址   /代表当前项目路径
        //forward：表示转发到一个地址
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee =  employeeDao.get(id);
        model.addAttribute("emp",employee);
        //查出部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    @PutMapping("/emp")
    public String update(Employee emp){
        System.out.println(emp);
        employeeDao.save(emp);
        return "redirect:/emps";
    }

    @DeleteMapping("emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
       return "redirect:/emps";
    }
}
