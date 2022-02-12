package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.services.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/sleepingMotorhome")
public class PersonTypeController {

    @Autowired
    private PersonTypeService personTypeService;

   /*@GetMapping(path = "/listPersonTypes")
    public String getPersonTypes (Model model){
        //model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "listPersonType";
    }*/


}
