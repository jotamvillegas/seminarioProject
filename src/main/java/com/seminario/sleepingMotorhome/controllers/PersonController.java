package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.services.PersonService;
import com.seminario.sleepingMotorhome.services.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping (path = "/sleepingMotorhome")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonTypeService personTypeService;


    @GetMapping(path = "/person/all")
    public String getAllUser (Model model){
        model.addAttribute("users", personService.listPerson());
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "person/all";
    }

    @PostMapping(path = "/person/savePerson")
    public String savePerson (@Valid Person person,
                              Errors errors,
                              @RequestParam (value = "perType") PersonType type,
                              Model model){

        if (errors.hasErrors()) {
            model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
            return "person/add";
        }
        if (person.getId() == null) {
            if (personService.existUsername(person.getUserName())) {
                model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
                model.addAttribute("msgErrorUsernameExisting",
                        "Ya existe un usuario con el mismo nombre. Por favor, ingresa un nombre de usuario distinto.");
                return "person/add";
            }
        }
        personService.savePerson(person, type);
        return "redirect:/sleepingMotorhome/person/all";

    }

    @GetMapping(path = "/person/add")
    public String add (Person person, PersonType personType, Model model){
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "person/add";
    }

    @GetMapping(path = "/person/edit/{id}")
    public String editUser (Person person, PersonType personType, Model model){
        Person personToEdit = personService.getPersonById(person.getId());
        model.addAttribute("person", personToEdit);
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes()); // pasar lista completa al model
        model.addAttribute("editMode","true");
        return "person/add";
    }

    @GetMapping (path = "/person/delete/{id}")
    public String deleteUser (@PathVariable ("id") Long id, Model model){
        personService.deletePerson(id);
        return "redirect:/sleepingMotorhome/person/all";
    }


}
