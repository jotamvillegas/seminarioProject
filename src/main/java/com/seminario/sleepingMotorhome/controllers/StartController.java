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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
        var person = personService.allPerson();
        model.addAttribute("person", person);
        return "index";
    }

    // person controller

    @GetMapping(path = "/add")
    public String add (Person person, PersonType personType, Model model){
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "add";
    }

    @PostMapping(path = "/savePerson")
    public String savePerson (Person person, User user){
        System.out.println(person.toString());
        personService.savePerson(person);
        userService.saveUser(user);
        return "redirect:all";
    }

    @GetMapping(path = "/all")
    public String getAllUser (Model model){
        model.addAttribute("users", personService.allPerson());
        return "all";
    }




}
