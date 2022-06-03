package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.repositories.StatusRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusRolService {

    @Autowired
    private StatusRolRepository statusRolRepository;

    public List<StatusRol> getStatusRoles(){
        return (List<StatusRol>) statusRolRepository.findAll();
    }

    public void saveStatusRol(StatusRol statusRol){
        statusRolRepository.save(statusRol);
    }

    public void deleteStatusRol(StatusRol statusRol){
        statusRolRepository.delete(statusRol);
    }

    public StatusRol getStatusRolById(StatusRol statusRol){
        return statusRolRepository.findById(statusRol.getId()).orElse(null);
    }

    public boolean existStatusRol (Long id){
        return statusRolRepository.existsById(id);
    }

    public StatusRol getStatusRol (String type){
        if (!statusRolRepository.existsRolByName(type)) {
            return null;
        }
        return statusRolRepository.getByStatusRol(type);
    }

}
