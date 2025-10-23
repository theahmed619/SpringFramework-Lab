package com.ems.service;

import com.ems.entity.Employee;
import com.ems.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;

    public Employee addEmployee(Employee employee){
        return empRepo.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return empRepo.findAll();
    }
}
