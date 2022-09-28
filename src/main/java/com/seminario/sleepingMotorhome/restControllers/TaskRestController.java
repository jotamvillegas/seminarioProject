package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Task;
import com.seminario.sleepingMotorhome.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/task", produces = {"application/json"})
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody List<Task> getAllEmployee(){
        return taskService.getAll();
    }

}
