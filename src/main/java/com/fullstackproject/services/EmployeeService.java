package com.fullstackproject.services;

import com.fullstackproject.entities.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAll();

    public Employee getEmployeeById(int id);
    public String deleteEmployee(int id);
    public Employee updateEmployee(int id,Employee employee);
    public Employee getEmployeeByEmail(String email);

    public Employee addEmployee(Employee employee);
}
