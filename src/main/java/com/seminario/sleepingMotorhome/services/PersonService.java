package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<Person> listPerson(){
        return (List<Person>) personRepository.findAll();
    }

    //@Transactional
    public void savePerson(Person person){
        personRepository.save(person);
    }

    public void deletePerson(Person person){
        personRepository.delete(person);
    }

    public Person searchPerson(Person person) {
        return personRepository.findById(person.getId()).orElse(null);
    }


}
