package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Admin;
import com.seminario.sleepingMotorhome.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping(path = "/all")
    public String getAllAdmin (Model model){
        model.addAttribute("admin", adminService.getAll());
        return "admin/all";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Admin admin, Errors errors, Model model){
        if (errors.hasErrors()) {
            return "admin/add";
        }
        if (admin.getId() == null) {
            if (adminService.existEmployee(admin.getUserName())) {
                model.addAttribute("msgErrorUsernameExisting",
                        "Ya existe un usuario con el mismo nombre. Por favor, ingresa un nombre de usuario distinto.");
                return "admin/add";
            }
        }
        adminService.save(admin);
        return "redirect:/sleepingMotorhome/admin/all";
    }

    @GetMapping(path = "/add")
    public String add (Admin admin, Model model){
        return "admin/add";
    }

    @GetMapping(path = "/edit/{id}")
    public String editAdmin (Admin admin, Model model){
        Admin a = adminService.getAdminById(admin.getId());
        model.addAttribute("admin", a);
        model.addAttribute("editMode","true");
        return "admin/add";
    }
    
    @GetMapping (path = "/delete/{id}")
    public String deleteAdmin (@PathVariable("id") Long id, Model model){
        adminService.delete(id);
        return "redirect:/sleepingMotorhome/admin/all";
    }
}
