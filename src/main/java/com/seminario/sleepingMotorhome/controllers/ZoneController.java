package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;



    @GetMapping(path = "/all")
    public String getAllServices (Model model){
        model.addAttribute("tasks", zoneService.getAll());
        return "zone/all";
    }

    @GetMapping(path = "/add")
    public String add (Zone zone, Model model){
        return "zone/add";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Zone zone,
                        Errors errors, Model model){
        if (errors.hasErrors()) {
            return "zone/add";
        }
        zoneService.save(zone);
        return "redirect:/sleepingMotorhome/zone/all";

    }

    @GetMapping(path = "/edit/{id}")
    public String edit (Zone zone, Model model){
        Zone z = zoneService.getZone(zone.getId());
        model.addAttribute("zone", z);
        model.addAttribute("editMode","true");
        return "zone/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model){
        zoneService.delete(id);
        return "redirect:/sleepingMotorhome/zone/all";
    }

}
