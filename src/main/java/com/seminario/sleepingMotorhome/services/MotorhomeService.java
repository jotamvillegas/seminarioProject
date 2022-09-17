package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.repositories.MotorhomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MotorhomeService {

    @Autowired
    private MotorhomeRepository motorhomeRepository;
    @Autowired
    private MotorhomeTypeService motorhomeTypeService;
    @Autowired
    private GarageService garageService;
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private UserService userService;

    public List<Motorhome> motorhomeListStatusActive() {
        return motorhomeRepository.motorhomesListActived(1);
    }

    public void save(Motorhome motorhome, Zone zone, MotorhomeType motorhomeType, Garage garage, Person person){
        motorhomeRepository.save(createMotorhome(motorhome, zone, motorhomeType, garage, person));
    }

    public void deleteMotorhome (Long id){
        motorhomeRepository.deleteById(id);
    }

    public void finalizeMotorhome (Long id){
        Motorhome editMotorhome = getMotorhomeById(id);
        editMotorhome.setGarage(null);
        editMotorhome.setIsActive(0);
        Date date = new Date();
        editMotorhome.setDateOfEgress(date);
        motorhomeRepository.save(editMotorhome);
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public Motorhome getMotorhomeById (Long id){
        return motorhomeRepository.findById(id).orElse(null);
    }

    public List<Motorhome> getMotorhomeByUserId (Long id){
        return motorhomeRepository.findByUserId(id);
    }

    public List<Motorhome> getMotorhomeActivesByUserId (Long id){
        return motorhomeRepository.findMotorhomesActiveByUserId(id);
    }

    public boolean existMotorhome (Long id){
        return motorhomeRepository.existsById(id);
    }

    public List<Long> motorhomeListById (){
        List<Long> motorhomeListOnlyId = new ArrayList<>();
        for (Motorhome temp : motorhomeListStatusActive()) {
            motorhomeListOnlyId.add(temp.getId());
        }
        return motorhomeListOnlyId;
    }

    private Motorhome createMotorhome (Motorhome motorhome, Zone zone, MotorhomeType motorhomeType, Garage garage, Person person){
        Motorhome newMotorhome = null;
        List<Long> garageFreeList = garageService.garageFreeListOnlyId();

        if (motorhome.getId() == null){
            // si el id motorhome en nuevo setea el new garage en ocupado
            if (garageFreeList.contains(garage.getId())) {
                if (zoneService.existZone(motorhomeType.getId())){
                    garage.setZone(zoneService.getZone(motorhomeType.getId()));
                }
                garage.setDateOfAdmission(new Date());
                garage.setGarageNumber(garage.getId().intValue());
                garage.setGarageStatus(true);
                garageService.saveGarage(garage);
            }
            newMotorhome = new Motorhome();

        } else {
            newMotorhome = getMotorhomeById(motorhome.getId());
            // si hay que editar el motorhome y hay cambio de garage debe setear el garage old en false y el nuevo en true
            if (!newMotorhome.getGarage().getId().equals(garage.getId())){
                Garage garageOld = garageService.getGarage(newMotorhome.getGarage().getId());
                garageOld.setDateOfEgress(new Date());
                garageOld.setGarageStatus(false);
                garageService.saveGarage(garageOld);
            }
            if (zoneService.existZone(motorhomeType.getId())){
                garage.setZone(zoneService.getZone(motorhomeType.getId()));
            }
            garage.setGarageNumber(garage.getId().intValue());
            garage.setGarageStatus(true);
            garageService.saveGarage(garage);

        }

        newMotorhome.setEnrollment(motorhome.getEnrollment().toUpperCase());
        newMotorhome.setLengthMotorhome(motorhome.getLengthMotorhome());
        newMotorhome.setWidthMotorhome(motorhome.getWidthMotorhome());
        newMotorhome.setGarage(garageService.getGarage(garage.getId()));
        newMotorhome.setMotorhomeType(motorhomeTypeService.getMotorhomeType(motorhomeType.getId()));
        newMotorhome.setUser(userService.getUserById(person.getId()));
        newMotorhome.setDateOfAdmission(motorhome.getDateOfAdmission());
        newMotorhome.setDateOfEgress(null);
        newMotorhome.setRentalDays(motorhome.getRentalDays());
        newMotorhome.setIsActive(1);

        return newMotorhome;
    }

}
