package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.User;
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



    @GetMapping ("/index")
    public String start (Model model){
        var person = personService.listPerson();
        model.addAttribute("person", person);
        return "index";
    }

    // person controller

    @GetMapping(path = "/all")
    public String getAllUser (Model model){
        model.addAttribute("users", personService.listPerson());
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "all";
    }

    @GetMapping(path = "/edit")
    public String add (Person person, PersonType personType, Model model){
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "edit";
    }

    @PostMapping(path = "/savePerson")
    public String savePerson (@RequestParam(value = "perType") PersonType type,  User user){
        user.setDateOfAdmission(new Date());
        user.setPersonType(type);
        userService.saveUser(user);
        return "redirect:all";
    }

    @GetMapping(path = "/edit/{id}")
    public String editUser(Person person, PersonType personType, Model model){
        person = personService.searchPerson(person);
        model.addAttribute("person", person);
        //model.addAttribute("listPersonTypes", personTypeService.searchPersonType(person.getPersonType()));
        return "edit";
    }




}
