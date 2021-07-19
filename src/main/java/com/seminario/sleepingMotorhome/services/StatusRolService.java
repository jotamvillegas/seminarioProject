package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.repositories.StatusRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusRolService {

    @Autowired
    private StatusRolRepository statusRolRepository;

    public StatusRol getStatusRol (String type){
        if (!statusRolRepository.existsRolByName(type)) {
            return null;
        }
        return statusRolRepository.getByStatusRol(type);
    }



}
