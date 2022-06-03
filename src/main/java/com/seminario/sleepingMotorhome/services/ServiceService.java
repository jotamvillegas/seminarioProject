package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.seminario.sleepingMotorhome.models.Service> serviceList (){
        return (List<com.seminario.sleepingMotorhome.models.Service>) serviceRepository.findAll();
    }

    public void saveService (com.seminario.sleepingMotorhome.models.Service service){
        serviceRepository.save(service);
    }

    public void delete (Long id){
        serviceRepository.deleteById(id);
    }

    public com.seminario.sleepingMotorhome.models.Service getService (Long id){
        return serviceRepository.findById(id).orElse(null);
    }

    public boolean existService (Long id){
        return serviceRepository.existsById(id);
    }

}
