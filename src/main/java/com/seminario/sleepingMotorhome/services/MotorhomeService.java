package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.repositories.MotorhomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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
        Date date = new Date();
        Motorhome editMotorhome = getMotorhomeById(id);
        editMotorhome.setGarage(null);
        editMotorhome.setIsActive(0);
        editMotorhome.setDateOfEgress(convertToLocalDateViaInstant(date));
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
            newMotorhome.setEnrollment(motorhome.getEnrollment().toUpperCase());
            newMotorhome.setLengthMotorhome(motorhome.getLengthMotorhome());
            newMotorhome.setWidthMotorhome(motorhome.getWidthMotorhome());
            newMotorhome.setGarage(garageService.getGarage(garage.getId()));
            newMotorhome.setMotorhomeType(motorhomeTypeService.getMotorhomeType(motorhomeType.getId()));
            newMotorhome.setUser(userService.getUserById(person.getId()));
            newMotorhome.setDateOfAdmission(String.valueOf(motorhome.getDateOfAdmission()));
            newMotorhome.setDateOfEgress(null);
            newMotorhome.setRentalDays(motorhome.getRentalDays());
            newMotorhome.setIsActive(1);

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

            newMotorhome.setEnrollment(motorhome.getEnrollment().toUpperCase());
            newMotorhome.setLengthMotorhome(motorhome.getLengthMotorhome());
            newMotorhome.setWidthMotorhome(motorhome.getWidthMotorhome());
            newMotorhome.setGarage(garageService.getGarage(garage.getId()));
            newMotorhome.setMotorhomeType(motorhomeTypeService.getMotorhomeType(motorhomeType.getId()));
            newMotorhome.setUser(userService.getUserById(person.getId()));
            newMotorhome.setDateOfAdmission(String.valueOf(motorhome.getDateOfAdmission()));
            newMotorhome.setDateOfEgress(null);
            newMotorhome.setRentalDays(motorhome.getRentalDays());
            newMotorhome.setIsActive(1);
        }

        return newMotorhome;
    }



    /*public static void main(String[] args) {

        Date value =  new Date();
        System.out.println("new date: " + value);
        System.out.println("-------------------------");

        // fecha de ingreso
        String dateInString = "2022-09-11"; //lo que me viene del front
        LocalDate date = LocalDate.parse(dateInString);
        System.out.println("localDate convert: " + date);
        System.out.println("-------------------------");

        // fecha de egreso
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        LocalDateTime fecha = LocalDateTime.of(hoy, ahora);
        System.out.println("localDate2 convert: " + fecha);



    }*/

}
