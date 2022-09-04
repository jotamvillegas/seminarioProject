package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private GarageService garageService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private ServiceTypeService serviceTypeService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/all")
    public String getAllServices (Employee employee, Service service, ServiceType serviceType, Model model){
        List<Task> value = taskService.getAll();
        model.addAttribute("tasks", taskService.getAll());
        return "task/all";
    }

    @GetMapping(path = "/add")
    public String add (Task task, Service service, Garage garage, ServiceType serviceType, Person person, Model model){
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("serviceList", serviceService.serviceList());
        model.addAttribute("serviceTypeList", serviceTypeService.serviceTypeList());
        model.addAttribute("garageList", garageService.garageList());
        return "task/add";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Task task,
                        @RequestParam(value = "employ") Person person,
                        @RequestParam(value = "serv") Service service,
                        @RequestParam(value = "gara") Garage garage,
                        @RequestParam(value = "servType") ServiceType serviceType,
                        Errors errors, Model model){
        if (errors.hasErrors()) {
            return "task/add";
        }
        taskService.save(task, person, service, garage, serviceType);
        return "redirect:/sleepingMotorhome/task/all";

    }

    @GetMapping(path = "/edit/{id}")
    public String edit (Task task, Service service, Garage garage, ServiceType serviceType, Person person, Model model){
        Task t = taskService.getTaskById(task.getId());
        model.addAttribute("task", t);
        model.addAttribute("employeeList", employeeService.getAll());
        model.addAttribute("serviceList", serviceService.serviceList());
        model.addAttribute("serviceTypeList", serviceTypeService.serviceTypeList());
        model.addAttribute("garageList", garageService.garageList());
        model.addAttribute("editMode","true");
        return "task/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model){
        taskService.delete(id);
        return "redirect:/sleepingMotorhome/task/all";
    }


}
