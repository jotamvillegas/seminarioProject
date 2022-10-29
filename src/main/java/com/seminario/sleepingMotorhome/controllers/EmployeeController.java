package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.models.Task;
import com.seminario.sleepingMotorhome.services.EmployeeService;
import com.seminario.sleepingMotorhome.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/sleepingMotorhome/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping(path = "/data")
    public String employeeData (Authentication auth, Model model){
        Employee personToEdit = employeeService.getEmployeeByUserName(auth.getName());
        //List<Task> taskList = taskService.getServiceByEmployee (personToEdit);
        List<Task> activeTaskList = taskService.getListTaskByEmployee (personToEdit.getId());
        model.addAttribute("employee", personToEdit);
        model.addAttribute("tasks", activeTaskList);
        model.addAttribute("editMode","true");
        return "employees/employee";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/all")
    public String getAllEmployees (Model model){
        model.addAttribute("employee", employeeService.getAll());
        return "employees/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/save")
    public String save (@Valid Employee employee, Errors errors, Model model){
        if (errors.hasErrors()) {
            return "employees/add";
        }
        if (employee.getId() == null) {
            if (employeeService.existEmployee(employee.getUserName())) {
                model.addAttribute("msgErrorUsernameExisting",
                        "Ya existe un usuario con el mismo nombre. Por favor, ingresa un nombre de usuario distinto.");
                return "employees/add";
            }
        }
        employeeService.save(employee);
        return "redirect:/sleepingMotorhome/employee/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/add")
    public String add (Employee employee, Model model){
        return "employees/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/edit/{id}")
    public String editEmployee (Employee employee, Model model){
        Employee e = employeeService.getEmployeeById(employee.getId());
        model.addAttribute("employee", e);
        model.addAttribute("editMode","true");
        return "employees/add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping (path = "/delete/{id}")
    public String deleteEmployee (@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs){
        if (employeeService.delete(id)){
            redirAttrs.addFlashAttribute("success", "Se eliminó el empleado correctamente.");
            return "redirect:/sleepingMotorhome/employee/all";
        }
        redirAttrs. addFlashAttribute ( "error" , "No se puede eliminar un empleado por tener tareas activas. " +
                "Por favor, finalice las tareas asignadas y luego elimine el empleado.");
        return "redirect:/sleepingMotorhome/employee/all";
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @GetMapping (path = "/finalize/{id}")
    public String finalizeTask (@PathVariable ("id") Long id, Model model, RedirectAttributes redirAttrs){

        Task m = taskService.getTaskById(id);
        if (m.getIsActive() == 1) {
            taskService.finalizeTask(id);
            redirAttrs.addFlashAttribute("success", "El proceso finalizó correctamente");
            return "redirect:/sleepingMotorhome/employee/data";

        }
        redirAttrs. addFlashAttribute ( "error" , "No se puede finalizar el proceso hasta que el empleado " +
                "finalice su tarea");
        return "redirect:/sleepingMotorhome/employee/data";
    }

}
