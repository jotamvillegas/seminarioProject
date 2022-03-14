package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.services.PersonService;
import com.seminario.sleepingMotorhome.services.PersonTypeService;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping (path = "/sleepingMotorhome")
public class StartController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private UserService userService;


    @GetMapping (value = {"/login","/"})
    public String start (){
        return "index";
    }

    // person controller

    @GetMapping(path = "/all")
    public String getAllUser (Model model){
        model.addAttribute("users", personService.listPerson());
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "all";
    }

    @GetMapping(path = "/add")
    public String add (Person person, PersonType personType, Model model){
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "add";
    }

    @PostMapping(path = "/savePerson")
    public String savePerson (@RequestParam(value = "perType") PersonType type,  Person person){
        person.setDateOfAdmission(new Date());
        person.setPersonType(type);
        personService.savePerson(person);
        return "redirect:all";
    }

    @GetMapping(path = "/edit/{id}")
    public String editUser (Person person, PersonType personType, Model model){
        Person personToEdit = personService.getPersonById(person.getId());
        PersonType personTypeToEdit = personToEdit.getPersonType();
        model.addAttribute("person", personToEdit);
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        model.addAttribute("editMode","true");
        return "add";
    }

    @GetMapping (path = "/delete/{id}")
    public String deleteUser (@PathVariable ("id") Long id, Model model){
        personService.deletePerson(id);
        return "redirect:/sleepingMotorhome/all";
    }





}
