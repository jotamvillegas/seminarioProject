package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Admin;
import com.seminario.sleepingMotorhome.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/admin", produces = {"application/json"})
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @GetMapping(path = "/rest-all")
    public @ResponseBody List<Admin> getAllAdmin(){
        return adminService.getAll();
    }

}
