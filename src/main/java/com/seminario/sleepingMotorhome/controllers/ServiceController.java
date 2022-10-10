package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Service;
import com.seminario.sleepingMotorhome.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;


    @GetMapping(path = "/all")
    public String getAll (Model model){
        model.addAttribute("service", serviceService.serviceList());
        return "service/all";
    }

    @GetMapping(path = "/add")
    public String add (Service service, Model model){
        return "service/add";
    }

    @PostMapping(path = "/save")
    public String save (@Valid Service service,
                        Errors errors, Model model){
        if (errors.hasErrors()) {
            return "service/add";
        }
        serviceService.save(service);
        return "redirect:/sleepingMotorhome/service/all";

    }

    @GetMapping(path = "/edit/{id}")
    public String edit (Service service, Model model){
        Service s = serviceService.getServiceType(service.getId());
        model.addAttribute("service", s);
        model.addAttribute("editMode","true");
        return "service/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String delete (@PathVariable("id") Long id, Model model){
        serviceService.delete(id);
        return "redirect:/sleepingMotorhome/service/all";
    }


}
