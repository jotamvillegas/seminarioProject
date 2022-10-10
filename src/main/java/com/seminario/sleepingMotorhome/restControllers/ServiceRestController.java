package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Service;
import com.seminario.sleepingMotorhome.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/service", produces = {"application/json"})
public class ServiceRestController {

    @Autowired
    private ServiceService serviceService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody
    List<Service> getAllZone(){
        return serviceService.serviceList();
    }

}
