package com.example.demo.models;

public class Department
{
    private int deptNo;
    private String deptName;
    private String location;

    public Department(int deptNo, String deptName, String location)
    {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.location = location;
    }

    public int getDeptNo()
    {
        return this.deptNo;
    }

    public void setDeptNo(int deptNo)
    {
        this.deptNo = deptNo;
    }

    public String getDeptName()
    {
        return this.deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return "Department{" +
                "deptno=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
