package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import com.seminario.sleepingMotorhome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private StatusRolService statusRolService;

    public User addUser(String userName, String password, String fullName, String surname, Integer document
                        , String addressName , Integer addressNumber, String floor, Integer phone, String personType){
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setName(fullName);
        user.setSurname(surname);
        user.setDocumentNumber(document);
        user.setAddressName(addressName);
        user.setAddressNumber(addressNumber);
        user.setFloor(floor);
        user.setPhone(phone);
        user.setDateOfAdmission(Date.from(Instant.now()));
        user.setDateOfEgress(Date.from(Instant.now()));
        user.setPersonType(getPersonsTypes(personType));
        user.setStatusRol(getStatusRol("Activo"));
        userRepository.save(user);
        return user;
    }

    private PersonType getPersonsTypes (String personType){
        return personTypeService.getPersonTypes(personType);
    }

    private StatusRol getStatusRol (String statusRol){
        return statusRolService.getStatusRol(statusRol);
    }


    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }


}
