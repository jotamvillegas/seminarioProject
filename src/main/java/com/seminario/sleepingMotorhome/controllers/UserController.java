package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping (path = "/sleepingMotorhome")
public class UserController {

    @Autowired
    private UserService userService;

    // crea un usuario nuevo
    @PostMapping(path = "/addUser")
    public @ResponseBody User addUser(
                                @RequestParam String userName,
                                @RequestParam String password,
                                @RequestParam String fullName,
                                @RequestParam String surname,
                                @RequestParam Integer document,
                                @RequestParam String addressName,
                                @RequestParam Integer addressNumber,
                                @RequestParam String floor,
                                @RequestParam Integer phone,
                                @RequestParam String personType){
        return  userService.addUser(userName, password, fullName, surname, document, addressName
                , addressNumber, floor, phone, personType);
    }

    // retorna todos los usuarios
    @GetMapping(path = "/index")
    public String getAllUser (){
        return "index";
    }

    // retorna todos los usuarios
    @GetMapping(path = "/getAllUser")
    public String getAllUser (Model model){
        model.addAttribute("users", userService.getAllUser());
        return "getAllUser";
    }


}
