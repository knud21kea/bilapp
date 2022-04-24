package com.example.demo.controllers;

import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.repositories.IRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("departments", departmentRepository.getAllEntities());
        model.addAttribute("employees", employeeIRepository.getAllEntities());
        return "new-employee";
    }

    @PostMapping("/create")
    public String addEmployee() {
        return "redirect:/created";
    }

    @GetMapping("/created")
    public String addedEmployee(@RequestParam String name, String job, int manager, int salary, int dept, Model model) {
        employeeIRepository.create(new Employee(0, name, job, manager, null, salary, 0, dept));
        model.addAttribute("name", name.toUpperCase());
        model.addAttribute("job", job.toUpperCase());
        return "added-employee";
    }
}