package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Garage;
import com.seminario.sleepingMotorhome.models.Task;
import com.seminario.sleepingMotorhome.models.Zone;
import com.seminario.sleepingMotorhome.repositories.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GarageService {

    @Autowired
    private GarageRepository garageRepository;
    @Autowired
    private TaskService taskService;


    public List<Garage> garageList (){
        return (List<Garage>) garageRepository.findAll();
    }

    public void saveGarage (Garage garage){
        garageRepository.save(garage);
    }

    @Transactional
    public void save (Garage garage, Zone zone, String cantToCreate){
        for (int i = 0; i < Integer.valueOf(cantToCreate); i++) {
            Garage g = new Garage();
            String val = garageRepository.getMaxValueGarageNumber(zone.getId());
            if (val == null) val = "0";
            g.setGarageNumber(Integer.valueOf(val) + 1);
            g.setGarageStatus(false);
            g.setZone(zone);
            g.setDateOfCreation(new Date());
            garageRepository.save(g);
        }
    }

    @Transactional
    public void editGarage (Garage garage, Zone zone, String cantToCreate){
        Garage g = getGarage(garage.getId());
        /*g.setGarageNumber(garage.getGarageNumber());
        g.setGarageStatus();
        g.setDateOfAdmission();
        g.setZone();*/
        garageRepository.save(g);
    }

    public boolean deleteGarage (Long id){
        boolean hasFree = garageRepository.getGarageStatus(id);
        List<Task> hasTask = taskService.getTasksByGarage(id);
        if (hasFree == false && hasTask.size() == 0){
            garageRepository.deleteById(id);
            return true;
        }
        return false;
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

    public int cantGarageByZone (Long zoneId){
        return garageRepository.getCantGarageByZone(zoneId);
    }

    public List<Garage> getGaragesByStatusFreeAndZone (Long zoneId){
        return garageRepository.getAllGarageByStatusFreeAndZone(zoneId);
    }

    public List<Garage> listGarageByZone (Long zoneId){
        return garageRepository.getListGarageByZone(zoneId);
    }

}
