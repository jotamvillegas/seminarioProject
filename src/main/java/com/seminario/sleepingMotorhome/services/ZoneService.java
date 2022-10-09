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

    public List<Zone> getAll (){
        return (List<Zone>) zoneRepository.findAll();
    }

    @Transactional
    public void save (Zone zone){
        Zone z;
        if (zone.getId() == null) {
            z = new Zone();
            z.setDateOfCreation(new Date());
        }
        else {
            z = getZone(zone.getId());
        }
        z.setZoneName(zone.getZoneName().toUpperCase());
        z.setMotorhomeType(zone.getMotorhomeType());
        z.setGarageAmount(zone.getGarageAmount());
        zoneRepository.save(z);
    }

    public void delete (Long id){
        zoneRepository.deleteById(id);
    }

    public Zone getZone (Long id){
        return zoneRepository.findById(id).orElse(null);
    }

    public boolean existZone (Long id){
        return zoneRepository.existsById(id);
    }

}
