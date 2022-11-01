package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Motorhome;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sleepingMotorhome/user", produces = {"application/json"})
public class UserRestController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-all")
    public @ResponseBody List<User> getAllUser(){
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-giveUsername/{name}/{surname}")
    public @ResponseBody List<String> giveUsernames (@PathVariable("name") String name,
                                                     @PathVariable("surname") String surname ){
        return userService.giveUsername(name, surname);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/rest-edit/{id}")
    public @ResponseBody
    User getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}
