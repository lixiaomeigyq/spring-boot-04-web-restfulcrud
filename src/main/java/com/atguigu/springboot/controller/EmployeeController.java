package com.atguigu.springboot.controller;

import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import java.util.Collection;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    //查询所有员工页面
    @GetMapping("/emps")
    public String emps(Model model, Map map, ModelMap mp){
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中 model map  modelMap
        model.addAttribute("emps",employees);
        //thymeleaf默认会拼串
        return "emp/list";
    }

}
