package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Motorhome;
import com.seminario.sleepingMotorhome.services.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/motorhome", produces = {"application/json"})
public class MotorhomeRestController {

    @Autowired
    private MotorhomeService motorhomeService;

    @GetMapping(path = "/rest-all")
    public @ResponseBody List<Motorhome> getAllMotorhome(){
        return motorhomeService.motorhomeListStatusActive();
    }

    @GetMapping(path = "/rest-edit/{id}")
    public @ResponseBody Motorhome getMotorhome(@PathVariable("id") Long id){
        return motorhomeService.getMotorhomeById(id);
    }

}
