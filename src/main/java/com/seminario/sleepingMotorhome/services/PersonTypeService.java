package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonTypeService {

    @Autowired
    private PersonTypeRepository personTypeRepository;

    public PersonType savePersonType(PersonType personType){
        return personTypeRepository.save(personType);
    }

    public List<PersonType> getPersonTypes(){
        return (List<PersonType>) personTypeRepository.findAll();
    }


}
