package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Zone;
import com.seminario.sleepingMotorhome.repositories.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> zoneList (){
        return (List<Zone>) zoneRepository.findAll();
    }

    public void saveZone (Zone zone){
        zoneRepository.save(zone);
    }

    public void deleteZone (Long id){
        zoneRepository.deleteById(id);
    }

    public Zone getZone (Long id){
        return zoneRepository.findById(id).orElse(null);
    }

    public boolean existZone (Long id){
        return zoneRepository.existsById(id);
    }

}
