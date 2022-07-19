package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.services.PersonService;
import com.seminario.sleepingMotorhome.services.PersonTypeService;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping (path = "/sleepingMotorhome")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private UserService userService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    @GetMapping (value = {"/login","/"})
    public String start (){
        return "index";
    }

    @GetMapping (path = "/home")
    public String home (){
        return "home";
    }

    @GetMapping(path = "/person/all")
    public String getAllUser (Model model){
        model.addAttribute("users", personService.listPerson());
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "person/all";
    }

    @GetMapping(path = "/person/add")
    public String add (Person person, PersonType personType, Model model){
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "person/add";
    }

    @PostMapping(path = "/person/savePerson")
    public String savePerson (@Valid Person person,
                              Errors errors,
                              @RequestParam (value = "perType") PersonType type,
                              Model model){

        if (person.getId() == null){
            if (errors.hasErrors()) {
                model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
                return "person/add";
            }
            if (personService.existUsername(person.getUserName())){
                model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
                model.addAttribute("msgErrorUsernameExisting",
                        "Ya existe un usuario con el mismo nombre. Por favor, ingresa un nombre de usuario distinto.");
                return "person/add";
            }
            if (person.getPassword().length() < 30) {
                String pass = person.getPassword();
                String passEncode = person.setPassword(bCryptPasswordEncoder.encode(pass));
                person.setPassword(passEncode);
            }

        }

        person.setDateOfAdmission(new Date());
        person.setPersonType(type);
        // TODO: 17/7/2022 verificar mas adelante cuando un user queda inactivo.
        person.setStatusRol(personService.searchStatusRol("Active"));
        personService.savePerson(person);
        return "redirect:/sleepingMotorhome/person/all";

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
