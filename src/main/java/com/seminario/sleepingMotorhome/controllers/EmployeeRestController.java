package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/employee", produces = {"application/json"})
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody List<Employee> getAllEmployee(){
        return employeeService.getAll();
    }

}
