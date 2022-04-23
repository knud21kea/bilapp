package com.example.demo.repositories;

import com.example.demo.models.Employee;
import com.example.demo.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeRepository implements IRepository<Employee> {

    @Override
    public List<Employee> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Employee> allEmployees = new ArrayList<Employee>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Employee temp = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8)
                );
                allEmployees.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allEmployees;
    }

    @Override
    public Employee getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Employee temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees WHERE employees.id = '" + id + "'");
            ResultSet rs = pstmt.executeQuery();
                while (rs.next())
                {
                    temp = new Employee(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getDate(5),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getInt(8)
                    );
                }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return (temp == null) ? new Employee(0, "ID not found", "", 0, new Date(0), 0, 0, 0) : temp;
    }

    @Override
    public boolean create(Employee entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement
                    ("INSERT INTO employees (`employee_name`, `job`, `manager`, `hiredate`, `salary`, `commission`, `department_number`)" +
                            "VALUES (?,?,?,?,?,?,?);");

            preparedStatement.setString(1, entity.getEmpName().toUpperCase());
            preparedStatement.setString(2, entity.getEmpJob());
            preparedStatement.setInt(3, entity.getEmpMan());
            preparedStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(5, entity.getEmpSalary()); //)
            preparedStatement.setInt(6, entity.getEmpComm());
            preparedStatement.setInt(7, entity.getEmpDeptNo());
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
