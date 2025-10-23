package com.ems.controller;

import com.ems.entity.Employee;
import com.ems.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://spring-react-projects-1.vercel.app")
@RestController 
public class EmpController {

    @Autowired
    private EmpService empService;

    @PostMapping("/addemp")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        try{
            Employee emp=new Employee();
            emp.setName(employee.getName());
            emp.setEmail(employee.getEmail());
            emp.setPassword(employee.getPassword());
            return  new ResponseEntity<>(empService.addEmployee(emp), HttpStatus.CREATED);

        }catch (Exception ex){
            System.err.println("Error while adding employee: " + ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallemps")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = empService.getAllEmployees();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
            }
            return new ResponseEntity<>(employees, HttpStatus.OK); // 200 OK
        } catch (Exception ex) {
            System.err.println("Error while fetching employees: " + ex.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

}
