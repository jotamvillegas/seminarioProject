package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/sleepingMotorhome/motorhome")
public class MotorhomeController {

    @Autowired
    private MotorhomeService motorhomeService;
    @Autowired
    private MotorhomeTypeService motorhomeTypeService;
    @Autowired
    private GarageService garageService;
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private UserService userService;


    @GetMapping(path = "/all")
    public String getAllMotorhomes (Model model){
        model.addAttribute("motorhomes", motorhomeService.motorhomeListWithStatusTrue());
        return "motorhome/all";
    }

    @GetMapping(path = "/add")
    public String addMotorhome(Motorhome motorhome, Garage garage, Person person, Model model){
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("garageFreeList", garageService.garageFreeList());
        model.addAttribute("userList", userService.getAll());
        return "motorhome/add";
    }

    @PostMapping(path = "/save")
    public String saveMotorhome (@Valid Motorhome motorhome, Zone zone,
                                 @RequestParam (value = "mhType") MotorhomeType motorhomeType,
                                 @RequestParam (value = "gara") Garage garage,
                                 @RequestParam (value = "user") Person person,
                                 Errors errors, Model model){

        motorhomeService.save(motorhome, zone, motorhomeType, garage, person);
        return "redirect:/sleepingMotorhome/motorhome/all";
    }

    @GetMapping(path = "/edit/{id}")
    public String editMotorhome (Motorhome motorhome, Garage garage, Person person, Model model){
        Motorhome motorhomeToEdit = motorhomeService.getMotorhomeById(motorhome.getId());
        model.addAttribute("motorhome", motorhomeToEdit);
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("userList", userService.getAll());

        List<Garage> garageList = garageService.garageFreeList();
        Long value = motorhomeToEdit.getGarage().getId();
        Garage garageToEdit = garageService.getGarage(value);
        garageList.add(garageToEdit);
        model.addAttribute("garageFreeList", garageList);

        model.addAttribute("editMode","true");
        return "motorhome/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String deleteMotorhome (@PathVariable ("id") Long id, Model model){
        Garage garageOld = garageService.getGarage(motorhomeService.getMotorhomeById(id).getGarage().getId());
        garageOld.setDateOfEgress(new Date());
        garageOld.setGarageStatus(false);
        garageService.saveGarage(garageOld);
        motorhomeService.deleteMotorhome(id);
        return "redirect:/sleepingMotorhome/motorhome/all";
    }

}
