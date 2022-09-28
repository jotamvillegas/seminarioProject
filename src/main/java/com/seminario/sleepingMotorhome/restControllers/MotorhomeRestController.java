package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Motorhome;
import com.seminario.sleepingMotorhome.services.MotorhomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/motorhome", produces = {"application/json"})
public class MotorhomeRestController {

    @Autowired
    private MotorhomeService motorhomeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody List<Motorhome> getAllEmployee(){
        return motorhomeService.motorhomeListStatusActive();
    }

}
