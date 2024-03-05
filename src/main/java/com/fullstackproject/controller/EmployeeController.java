package com.fullstackproject.controller;

import com.fullstackproject.entities.Employee;
import com.fullstackproject.repositories.EmployeeRepository;
import com.fullstackproject.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {
@Autowired
   private EmployeeService employeeService;
Logger logger= LoggerFactory.getLogger(EmployeeController.class);
    @Cacheable("Employee")
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployee(){
        logger.info("Getting data of employee");
        long start=System.currentTimeMillis();
        List<Employee> all = employeeService.getAll();
        long end=System.currentTimeMillis();
        logger.info("process time is"+(end-start)+"ms");
        return new ResponseEntity<>( all,HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Employee> postEmployee(@Valid  @RequestBody Employee employee){
        Employee added = employeeService.addEmployee(employee);
        return  new ResponseEntity<>(added,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
       return  new  ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.FOUND);
    }
    @GetMapping()
    public  ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email){
        return new ResponseEntity<>(employeeService.getEmployeeByEmail(email),HttpStatus.FOUND);
    }
     @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable int id){
         String s = employeeService.deleteEmployee(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }
     @PutMapping("/{id}")
    public  ResponseEntity<Employee> updateEmployee(@Valid @PathVariable int id,@RequestBody Employee employee){
       return  new ResponseEntity<>(employeeService.updateEmployee(id,employee),HttpStatus.OK);
     }

}
