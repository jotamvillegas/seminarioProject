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
@RequestMapping(path = "/sleepingMotorhome")
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
    private PersonService personService;


    @GetMapping(path = "/motorhome/all")
    public String getAllMotorhomes (Model model){
        model.addAttribute("motorhomes", motorhomeService.motorhomeListWithStatusTrue());
        return "motorhome/all";
    }

    @GetMapping(path = "/motorhome/add")
    public String addMotorhome(Motorhome motorhome, Garage garage, Person person, Model model){
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("garageFreeList", garageService.garageFreeList());
        model.addAttribute("userList", personService.listPerson());
        return "motorhome/add";
    }

    @PostMapping(path = "/motorhome/save")
    public String saveMotorhome (@Valid Motorhome motorhome, Zone zone,
                                 @RequestParam (value = "mhType") MotorhomeType motorhomeType,
                                 @RequestParam (value = "gara") Garage garage,
                                 @RequestParam (value = "user") Person person,
                                 Errors errors, Model model){

        List<Long> garageFreeList = garageService.garageFreeListOnlyId();

        if (motorhome.getId() == null){
            // si el id motorhome en nuevo setea el new garage en ocupado
            if (garageFreeList.contains(garage.getId())) {
                if (zoneService.existZone(motorhomeType.getId())){
                    garage.setZone(zoneService.getZone(motorhomeType.getId()));
                }
                garage.setDateOfAdmission(new Date());
                garage.setGarageNumber(garage.getId().intValue());
                garage.setGarageStatus(true);
                garageService.saveGarage(garage);
            }
        } else {
            // si hay que editar el motorhome y hay cambio de garage debe setear el garage old en false y el nuevo en true
            if (garageFreeList.contains(garage.getId())) {
                Motorhome motorhomeToEdit = motorhomeService.getMotorhomeById(motorhome.getId());
                if (!motorhomeToEdit.getGarage().getId().equals(garage.getId())){
                    Garage garageOld = garageService.getGarage(motorhomeToEdit.getGarage().getId());
                    garageOld.setDateOfEgress(new Date());
                    garageOld.setGarageStatus(false);
                    garageService.saveGarage(garageOld);
                }
                if (zoneService.existZone(motorhomeType.getId())){
                    garage.setZone(zoneService.getZone(motorhomeType.getId()));
                }
                garage.setDateOfAdmission(new Date());
                garage.setGarageNumber(garage.getId().intValue());
                garage.setGarageStatus(true);
                garageService.saveGarage(garage);
            }
        }

        // validar si el user existe y setear

        motorhome.setEnrollment(motorhome.getEnrollment().toUpperCase());
        motorhome.setGarage(garage);
        motorhome.setMotorhomeType(motorhomeType);
        motorhomeService.saveMotorhome(motorhome);
        return "redirect:/sleepingMotorhome/motorhome/all";
    }

    @GetMapping(path = "/motorhome/edit/{id}")
    public String editUser (Motorhome motorhome, Garage garage, Person person, Model model){
        Motorhome motorhomeToEdit = motorhomeService.getMotorhomeById(motorhome.getId());
        model.addAttribute("motorhome", motorhomeToEdit);
        model.addAttribute("motorhomeTypeList", motorhomeTypeService.motorhomeTypeList());
        model.addAttribute("userList", personService.listPerson());

        List<Garage> garageList = garageService.garageFreeList();
        Long value = motorhomeToEdit.getGarage().getId();
        Garage garageToEdit = garageService.getGarage(value);
        garageList.add(garageToEdit);
        model.addAttribute("garageFreeList", garageList);

        model.addAttribute("editMode","true");
        return "motorhome/add";
    }

    @GetMapping (path = "/motorhome/delete/{id}")
    public String deleteUser (@PathVariable ("id") Long id, Model model){
        Garage garageOld = garageService.getGarage(motorhomeService.getMotorhomeById(id).getGarage().getId());
        garageOld.setDateOfEgress(new Date());
        garageOld.setGarageStatus(false);
        garageService.saveGarage(garageOld);
        motorhomeService.deleteMotorhome(id);
        return "redirect:/sleepingMotorhome/motorhome/all";
    }

}
