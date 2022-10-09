package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Zone;
import com.seminario.sleepingMotorhome.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/zone", produces = {"application/json"})
public class ZoneRestController {

    @Autowired
    private ZoneService zoneService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody
    List<Zone> getAllZone(){
        return zoneService.getAll();
    }

}
