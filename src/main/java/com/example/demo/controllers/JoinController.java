package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
 class JoinController
{
    private final IRepository<Employee> employeeIRepository = new EmployeeRepository();
    private final IRepository<Department> departmentRepository = new DepartmentRepository();

    @GetMapping("/bydept")
    public String byDepartment(@RequestParam String name, Model model)
    {
        model.addAttribute("deptName", name);
        model.addAttribute("departments", departmentRepository.getAllEntities());
        model.addAttribute("employees", employeeIRepository.getAllWithConstraint(name));
        return "bydept";
    }
}
