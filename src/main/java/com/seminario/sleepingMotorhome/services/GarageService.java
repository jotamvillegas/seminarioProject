package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Garage;
import com.seminario.sleepingMotorhome.repositories.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GarageService {

    @Autowired
    private GarageRepository garageRepository;

    public List<Garage> garageList (){
        return (List<Garage>) garageRepository.findAll();
    }

    public void saveGarage (Garage garage){
        garageRepository.save(garage);
    }

    public void deleteGarage (Long id){
        garageRepository.deleteById(id);
    }

    public Garage getGarage (Long id){
        return garageRepository.findById(id).orElse(null);
    }

    public boolean existGarage (Long id){
        return garageRepository.existsById(id);
    }

    public List<Garage> garageFreeList(){
        return garageRepository.findAllByGarageStatus(false);
    }

    public List<Long> garageFreeListOnlyId(){
        List<Long> garageFreeListOnlyId = new ArrayList<>();
        for (Garage temp : garageFreeList()) {
            garageFreeListOnlyId.add(temp.getId());
        }
        return garageFreeListOnlyId;
    }


}
