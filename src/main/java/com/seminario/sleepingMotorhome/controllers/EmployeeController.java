package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/sleepingMotorhome/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping(path = "/data")
    public String employeeData (Authentication auth, Model model){
        Employee personToEdit = employeeService.getEmployeeByUserName(auth.getName());
        model.addAttribute("person", personToEdit);
        return "employee/employees";
    }

    @GetMapping(path = "/all")
    public String getAllEmployees (Model model){
        model.addAttribute("employee", employeeService.getAll());
        return "employee/all";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Employee employee, Errors errors, Model model){
        if (errors.hasErrors()) {
            return "employee/add";
        }
        if (employee.getId() == null) {
            if (employeeService.existEmployee(employee.getUserName())) {
                model.addAttribute("msgErrorUsernameExisting",
                        "Ya existe un usuario con el mismo nombre. Por favor, ingresa un nombre de usuario distinto.");
                return "employee/add";
            }
        }
        employeeService.save(employee);
        return "redirect:/sleepingMotorhome/employee/all";
    }

    @GetMapping(path = "/add")
    public String add (Employee employee, Model model){
        return "employee/add";
    }

    @GetMapping(path = "/edit/{id}")
    public String editEmployee (Employee employee, Model model){
        Employee e = employeeService.getEmployeeById(employee.getId());
        model.addAttribute("employee", e);
        model.addAttribute("editMode","true");
        return "employee/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String deleteEmployee (@PathVariable("id") Long id, Model model){
        employeeService.delete(id);
        return "redirect:/sleepingMotorhome/employee/all";
    }


}
