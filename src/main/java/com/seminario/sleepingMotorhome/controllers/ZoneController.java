package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.services.MotorhomeTypeService;
import com.seminario.sleepingMotorhome.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/zone")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;
    @Autowired
    private MotorhomeTypeService motorhomeTypeService;


    @GetMapping(path = "/all")
    public String getAll (Model model){
        model.addAttribute("tasks", zoneService.getAll());
        return "zone/all";
    }

    @GetMapping(path = "/add")
    public String add (Zone zone, Model model){
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        return "zone/add";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Zone zone,
                        @RequestParam (value = "mhType") MotorhomeType motorhomeType,
                        Errors errors, Model model){
        if (errors.hasErrors()) {
            return "zone/add";
        }
        zoneService.save(zone, motorhomeType);
        return "redirect:/sleepingMotorhome/zone/all";

    }

    @GetMapping(path = "/edit/{id}")
    public String edit (Zone zone, MotorhomeType motorhomeType, Model model){
        Zone z = zoneService.getZone(zone.getId());
        model.addAttribute("zone", z);
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("editMode","true");
        return "zone/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs){
        if (zoneService.deleteProcessWasSuccessful(id)){
            redirAttrs. addFlashAttribute ( "success" , "Se eliminó correctamente la zona.") ;
            return "redirect:/sleepingMotorhome/zone/all";
        }
        redirAttrs.addFlashAttribute ( "error" , "No se puede eliminar la zona porque tiene garage relacionados. " +
                "Por favor, primero elimine los garages y luego elimine la zona correspondiente.");
        return "redirect:/sleepingMotorhome/zone/all";

    }

}
