package com.seminario.sleepingMotorhome.controllers;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.services.PersonService;
import com.seminario.sleepingMotorhome.services.PersonTypeService;
import com.seminario.sleepingMotorhome.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/all")
    public String getAllUser (Model model){
        model.addAttribute("users", personService.listPerson());
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "person/all";
    }

    @GetMapping(path = "/add")
    public String add (Person person, PersonType personType, Model model){
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        return "person/add";
    }

    @PostMapping(path = "/savePerson")
    public String savePerson (@RequestParam(value = "perType") PersonType type,  Person person){
        if (person.getPassword().length() < 30) {
            String pass = person.getPassword();
            String passEncode = person.setPassword(bCryptPasswordEncoder.encode(pass));
            person.setPassword(passEncode);
        }
        person.setDateOfAdmission(new Date());
        person.setPersonType(type);
        personService.savePerson(person);
        return "redirect:/sleepingMotorhome/all";
    }

    @GetMapping(path = "/edit/{id}")
    public String editUser (Person person, PersonType personType, Model model){
        Person personToEdit = personService.getPersonById(person.getId());
        PersonType personTypeToEdit = personToEdit.getPersonType();
        model.addAttribute("person", personToEdit);
        model.addAttribute("listPersonTypes", personTypeService.getPersonTypes());
        model.addAttribute("editMode","true");
        return "person/add";
    }

    @GetMapping (path = "/delete/{id}")
    public String deleteUser (@PathVariable ("id") Long id, Model model){
        personService.deletePerson(id);
        return "redirect:/sleepingMotorhome/all";
    }


}
