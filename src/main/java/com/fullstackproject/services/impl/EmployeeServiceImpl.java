package com.fullstackproject.services.impl;

import com.fullstackproject.entities.Employee;
import com.fullstackproject.exceptions.EmployeeAlreadyExistException;
import com.fullstackproject.exceptions.EmployeeNotFoundException;
import com.fullstackproject.repositories.EmployeeRepository;
import com.fullstackproject.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
   private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> all = employeeRepository.findAll();
        return all;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("Employee not exist with id " + id)
        );
        return employee;
    }

    @Override
    public String deleteEmployee(int id) {
        Employee employeeById = getEmployeeById(id);
        employeeRepository.deleteById(employeeById.getId());
        return "Employee record deleted";
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee employeeById = getEmployeeById(id);
        employeeById.setEmail(employee.getEmail());
        employeeById.setName(employee.getName());
        employeeById.setSalary(employee.getSalary());
        Employee save = employeeRepository.save(employeeById);

        return save;
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        return employee;
    }


    @Override
    public Employee addEmployee(Employee employee) {
        if(getEmployeeByEmail(employee.getEmail())!=null){
            throw new EmployeeAlreadyExistException("Email already taken");
        }
     return employeeRepository.save(employee);
    }
}
