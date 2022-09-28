package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Admin;
import com.seminario.sleepingMotorhome.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/admin", produces = {"application/json"})
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody List<Admin> getAllAdmin(){
        return adminService.getAll();
    }

}
