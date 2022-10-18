package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Garage;
import com.seminario.sleepingMotorhome.services.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/garage", produces = {"application/json"})
public class GarageRestController {

    @Autowired
    private GarageService garageService;

    @GetMapping(path = "/rest-all")
    public @ResponseBody
    List<Garage> getAllGarage(){
        return garageService.garageList();
    }

    // buscar garages libres por zona
    @GetMapping(path = "/rest-allFreeByZone/{id}")
    public @ResponseBody
    List<Garage> getAllGarageFreeByZone(@PathVariable("id") Long id){
        return garageService.getGaragesByStatusFreeAndZone(id);
    }


}
