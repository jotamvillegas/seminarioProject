package com.seminario.sleepingMotorhome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/sleepingMotorhome")
public class MainController {

    @GetMapping(value = {"/login","/"})
    public String start (){
        return "index";
    }

    @GetMapping (path = "/home")
    public String home (){
        return "home";
    }

}
