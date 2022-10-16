package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private GarageService garageService;


    public List<Zone> getAll (){
        return (List<Zone>) zoneRepository.findAll();
    }

    @Transactional
    public void save (Zone zone, MotorhomeType motorhomeType){
        Zone z;
        if (zone.getId() == null) {
            z = new Zone();
            z.setDateOfCreation(new Date());
        }
        else {
            z = getZone(zone.getId());
        }
        z.setZoneName(zone.getZoneName().toUpperCase());
        z.setMotorhomeType(motorhomeType);
        z.setGarageAmount(zone.getGarageAmount());
        zoneRepository.save(z);
    }

    public boolean deleteProcessWasSuccessful (Long zoneId){
        // La zona solo se elimina si es que no tiene garages asociados a ella
        List<Garage> listGarageByZone = garageService.listGarageByZone(zoneId);
        if (listGarageByZone.size() == 0){
            // elimino la zona que no tiene garage
            zoneRepository.deleteById(zoneId);
            return true;
        }
        return false;
    }

    public Zone getZone (Long id){
        return zoneRepository.findById(id).orElse(null);
    }

    public boolean existZone (Long id){
        return zoneRepository.existsById(id);
    }

    public Long getMotorhomeByZone (Long zoneId){
        return zoneRepository.getMotorhomeTypeIdByZoneId(zoneId);
    }


}
