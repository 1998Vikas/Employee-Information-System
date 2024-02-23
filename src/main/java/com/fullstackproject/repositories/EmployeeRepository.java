package com.fullstackproject.repositories;

import com.fullstackproject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

   public Employee findByEmail(String email);
}
