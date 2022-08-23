package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Motorhome;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.services.MotorhomeService;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/sleepingMotorhome/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MotorhomeService motorhomeService;

    @GetMapping(path = "/data")
    public String userData (Authentication auth, Model model){
        User personToEdit = userService.getUserByUserName(auth.getName());
        List<Motorhome> motorhomeList = motorhomeService.getMotorhomeByUserId(personToEdit.getId());
        model.addAttribute("user", personToEdit);
        model.addAttribute("mothomes", motorhomeList);
        return "users/user";
    }

    @GetMapping(path = "/all")
    public String getAllUsers (Model model){
        model.addAttribute("users", userService.getAll());
        return "users/all";
    }

    @PostMapping(path = "/save")
    public String save (@Valid User user, Errors errors, Model model, Authentication auth){
        String value = auth.getAuthorities().toString().replace('[',' ').replace(']',' ').trim();
        if (errors.hasErrors()) {
            return "users/add";
        }
        if (user.getId() == null) {
            if (userService.existUser(user.getUserName())) {
                model.addAttribute("msgErrorUsernameExisting",
                        "Ya existe un usuario con el mismo nombre. Por favor, ingresa un nombre de usuario distinto.");
                return "users/add";
            }
        }
        userService.save(user);
        if (value.equals("USER")){
            return "redirect:/sleepingMotorhome/home";
        }
        return "redirect:/sleepingMotorhome/user/all";

    }

    @GetMapping(path = "/add")
    public String add (User user, Model model){
        return "users/add";
    }

    @GetMapping(path = "/edit/{id}")
    public String editUser (User user, Model model){
        User u = userService.getUserById(user.getId());
        model.addAttribute("user", u);
        model.addAttribute("editMode","true");
        return "users/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String deleteUser (@PathVariable ("id") Long id, Model model){
        userService.delete(id);
        return "redirect:/sleepingMotorhome/user/all";
    }

}
