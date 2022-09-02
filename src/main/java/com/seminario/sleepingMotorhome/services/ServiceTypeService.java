package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.ServiceType;
import com.seminario.sleepingMotorhome.repositories.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTypeService {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public List<ServiceType> serviceTypeList (){
        return (List<ServiceType>) serviceTypeRepository.findAll();
    }

    public void saveServiceType (ServiceType serviceType){
        serviceTypeRepository.save(serviceType);
    }

    public void deleteServiceType (Long id){
        serviceTypeRepository.deleteById(id);
    }

    public ServiceType getServiceType (Long id){
        return serviceTypeRepository.findById(id).orElse(null);
    }

    public boolean existServiceType (String servType){
        return serviceTypeRepository.existsServiceTypeByDescription(servType);
    }

}
