package com.example.demo.models;

import java.util.Date;

public class Employee {
    private int empNo;
    private String empName;
    private String empJob;
    private int empMan;
    private Date empHireDate;
    private int empSalary;
    private int empComm;
    private int empDeptNo;

    public int getEmpNo()
    {
        return empNo;
    }

    public String getEmpName()
    {
        return empName;
    }

    public String getEmpJob()
    {
        return empJob;
    }

    public int getEmpMan()
    {
        return empMan;
    }

    public Date getEmpHireDate()
    {
        return empHireDate;
    }

    public int getEmpSalary()
    {
        return empSalary;
    }

    public int getEmpComm()
    {
        return empComm;
    }

    public int getEmpDeptNo()
    {
        return empDeptNo;
    }

    public Employee(int empNo, String empName, String empJob, int empMan, Date empHireDate, int empSalary, int empComm, int empDeptNo)
    {
        this.empNo = empNo;
        this.empName = empName;
        this.empJob = empJob;
        this.empMan = empMan;
        this.empHireDate = empHireDate;
        this.empSalary = empSalary;
        this.empComm = empComm;
        this.empDeptNo = empDeptNo;
    }
}
