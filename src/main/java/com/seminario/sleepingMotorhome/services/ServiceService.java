package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<com.seminario.sleepingMotorhome.models.Service> serviceList (){
        return (List<com.seminario.sleepingMotorhome.models.Service>) serviceRepository.findAll();
    }

    @Transactional
    public void save (com.seminario.sleepingMotorhome.models.Service service){
        com.seminario.sleepingMotorhome.models.Service s;
        if (service.getId() == null) {
            s = new com.seminario.sleepingMotorhome.models.Service();
            s.setDateOfCreation(new Date());
        }
        else {
            s = getServiceType(service.getId());
        }
        s.setDescription(service.getDescription());
        serviceRepository.save(s);
    }

    public void delete (Long id){
        serviceRepository.deleteById(id);
    }

    public com.seminario.sleepingMotorhome.models.Service getServiceType (Long id){
        return serviceRepository.findById(id).orElse(null);
    }

    public Integer numberOfTask (Long id){
        return serviceRepository.numberOfTask(id);
    }

    public Integer numberTaskFinalized (Long id){
        return serviceRepository.getTasksInFinishedState(id);
    }


}
