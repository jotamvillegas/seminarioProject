package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private StatusRolService statusRolService;


    public List<Person> listPerson(){
        return (List<Person>) personRepository.findAll();
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }

    public void deletePerson(Long personId){
        Person person = personRepository.findById(personId).orElse(null);
        personRepository.delete(person);
    }

    public Person getPersonById (Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public Person getPerson (Person person){
        return personRepository.findById(person.getId()).orElse(null);
    }

    public boolean existUsername(String username){
        return personRepository.existsPersonByUserName(username);
    }

    public List<Person> onlyUserList (){
        //return personRepository.personListByPersonTypeUser();
        return personRepository.findAllByPersonTypeEquals(3);
    }

    public Long getLastPersonSaved (){
        return personRepository.getLastRegister();
    }

    public StatusRol searchStatusRol (String status){
        return statusRolService.getStatusRol(status);
    }

}
