package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (path = "/sleepingMotorhome")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/createEmployee")
    public String createEmployee (Employee employee){
        employeeService.createEmployee(employee);
        return "Se creo el empleado exitosamente";
    }

}
