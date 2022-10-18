package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Garage;
import com.seminario.sleepingMotorhome.models.Zone;
import com.seminario.sleepingMotorhome.services.GarageService;
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
@RequestMapping(path = "/sleepingMotorhome/garage")
public class GarageController {

    @Autowired
    private GarageService garageService;

    @Autowired
    private ZoneService zoneService;


    @GetMapping(path = "/all")
    public String getAll (Model model){
        model.addAttribute("gara", garageService.garageList());
        return "garage/all";
    }

    @GetMapping(path = "/add")
    public String add (Garage garage, Zone zone, Model model){
        model.addAttribute("zoneList", zoneService.getAll());
        return "garage/add";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Garage garage,
                        @RequestParam (value = "zoneParam") Zone zone,
                        String cantToCreate,
                        Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("zoneList", zoneService.getAll());
            return "garage/add";
        }
        if (zone.getGarageAmount() < Integer.valueOf(cantToCreate)){
            model.addAttribute("zoneList", zoneService.getAll());
            model.addAttribute("msgError",
                        "El valor debe ser igual o menor que la cantidad de garages que ingreso al crear la zona.");
            return "garage/add";
        }
        if (zone.getGarageAmount() == garageService.cantGarageByZone(zone.getId())){
            model.addAttribute("zoneList", zoneService.getAll());
            model.addAttribute("msgError",
                    "La zona se encuentra completa, si desea agregar garage, modifique la cantidad de garage para " +
                        "la zona correspondiente o cree una nueva zona.");
            return "garage/add";
        }
        garageService.save(garage, zone, cantToCreate);
        return "redirect:/sleepingMotorhome/garage/all";

    }
/*
    @GetMapping(path = "/edit/{id}")
    public String edit (Garage garage, Zone zone, Model model){
        Garage g = garageService.getGarage(garage.getId());
        model.addAttribute("garage", g);
        model.addAttribute("zoneList", zoneService.getAll());
        model.addAttribute("editMode","true");
        return "garage/add";
    }

 */

    @GetMapping (path = "/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs){
        if (garageService.deleteGarage(id)){
            redirAttrs. addFlashAttribute ( "success" , "Se eliminó correctamente el garage.") ;
            return "redirect:/sleepingMotorhome/garage/all";
        }
        redirAttrs.addFlashAttribute ( "error" , "No se puede eliminar el garage porque se encuentra ocupado o tiene tareas pendientes. " +
                "Por favor, libere el garage del motorhome o finalice las tareas y luego eliminelo.");
        //garageService.deleteGarage(id);
        return "redirect:/sleepingMotorhome/garage/all";
    }


}
