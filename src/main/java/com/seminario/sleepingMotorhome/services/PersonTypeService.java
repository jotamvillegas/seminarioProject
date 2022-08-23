package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonTypeService {

    @Autowired
    private PersonTypeRepository personTypeRepository;


    public List<PersonType> getPersonTypes(){
        return (List<PersonType>) personTypeRepository.findAll();
    }

    public void savePersonType(PersonType personType){
        personTypeRepository.save(personType);
    }

    public void deletePersonType(PersonType personType){
        personTypeRepository.delete(personType);
    }

    public PersonType getPersonType(String type){
        return personTypeRepository.getByType(type);
    }

    public boolean existPersonType (Long id){
        return personTypeRepository.existsById(id);
    }
}
