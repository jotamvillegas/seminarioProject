package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public List<Person> allPerson (){
        return (List<Person>) personRepository.findAll();
    }
}
