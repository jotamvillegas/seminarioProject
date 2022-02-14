package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import com.seminario.sleepingMotorhome.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonTypeService personTypeService;


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



}
