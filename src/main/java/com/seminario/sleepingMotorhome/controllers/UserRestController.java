package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/user", produces = {"application/json"})
public class UserRestController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody List<User> getAllEmployee(){
        return userService.getAll();
    }

}
