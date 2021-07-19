package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/sleepingMotorhome/person/")
public class PersonController {

    @Autowired
    private PersonService personService;

    // busca una persona por nombre (usuario o empleado)
    @GetMapping(path = "/getPersonByName/{name}")
    public @ResponseBody Person getPersonByName (@PathVariable String name){
        return personService.getPersonByName(name);
    }
}
