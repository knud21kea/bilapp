package com.example.demo.controllers;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    private final IRepository<Employee> employeeIRepository = new EmployeeRepository();

    @GetMapping("/employees")
    public String allDepartments(Model model){
        model.addAttribute("employees", employeeIRepository.getAllEntities());
        return "employees";
    }

    @GetMapping("/employee")
    public String singleEmployee(@RequestParam int id, Model model)
    {
        model.addAttribute("employee", employeeIRepository.getSingleById(id));
        model.addAttribute("employees", employeeIRepository.getAllEntities());
        return "employee";
    }
}
