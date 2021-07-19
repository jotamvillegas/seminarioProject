package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonTypeService {

    @Autowired
    private PersonTypeRepository personTypeRepository;

    public PersonType getPersonTypes(String type){
        if (!personTypeRepository.existsByName(type)) {
            return null;
        }
        return personTypeRepository.getByType(type);
    }
}
