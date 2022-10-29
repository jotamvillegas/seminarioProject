package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/motorhome")
public class MotorhomeController {

    @Autowired
    private MotorhomeService motorhomeService;
    @Autowired
    private MotorhomeTypeService motorhomeTypeService;
    @Autowired
    private GarageService garageService;
    @Autowired
    private UserService userService;


    @GetMapping(path = "/all")
    public String getAllMotorhomes (Model model){
        model.addAttribute("motorhomes", motorhomeService.motorhomeListStatusActive());
        return "motorhome/all";
    }

    @GetMapping(path = "/add")
    public String addMotorhome(Motorhome motorhome, Garage garage, Person person, Model model){
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("userList", userService.getAll());
        return "motorhome/add";
    }

    @PostMapping(path = "/save")
    public String saveMotorhome (@Valid Motorhome motorhome, Zone zone,
                                 @RequestParam (value = "mhType") MotorhomeType motorhomeType,
                                 @RequestParam (value = "gara") Garage garage,
                                 @RequestParam (value = "user") Person person,
                                 String payment, String balance, String total,
                                 Errors errors, Model model){
        motorhomeService.save(motorhome, zone, motorhomeType, garage, person, payment, balance, total);
        return "redirect:/sleepingMotorhome/motorhome/all";
    }

    @GetMapping(path = "/edit/{id}")
    public String editMotorhome (Motorhome motorhome, Garage garage, Person person, Model model){
        Motorhome motorhomeToEdit = motorhomeService.getMotorhomeById(motorhome.getId());
        model.addAttribute("motorhome", motorhomeToEdit);
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("userList", userService.getAll());

        Long value = motorhomeToEdit.getGarage().getId();
        Garage garageToEdit = garageService.getGarage(value);
        model.addAttribute("gara", garageToEdit);

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

    @GetMapping (path = "/finalize/{id}")
    public String finalizeMotorhome (@PathVariable ("id") Long id, Model model, RedirectAttributes redirAttrs){
        Motorhome m = motorhomeService.getMotorhomeById(id);

        if (m.getBalance() != null) {
            if (m.getTotal() == m.getPayment() + m.getBalance()) {
                Garage garageOld = garageService.getGarage(motorhomeService.getMotorhomeById(id).getGarage().getId());
                garageOld.setDateOfEgress(new Date());
                garageOld.setGarageStatus(false);
                garageService.saveGarage(garageOld);
                motorhomeService.finalizeMotorhome(id);
                redirAttrs.addFlashAttribute("success", "El proceso de estadía finalizó correctamente");
                return "redirect:/sleepingMotorhome/motorhome/all";
            }
        }
        redirAttrs. addFlashAttribute ( "error" , "No se puede finalizar el proceso hasta que el usuario " +
                "cancele por completo su estadía");
        return "redirect:/sleepingMotorhome/motorhome/all";
    }

}
