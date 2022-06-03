package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Motorhome;
import com.seminario.sleepingMotorhome.repositories.MotorhomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotorhomeService {

    @Autowired
    private MotorhomeRepository motorhomeRepository;

    public List<Motorhome> motorhomeListWithStatusTrue() {
        return (List<Motorhome>) motorhomeRepository.motorhomesListWithStatusTrue(true);
    }

    public void saveMotorhome (Motorhome motorhome){
        motorhomeRepository.save(motorhome);
    }

    public void deleteMotorhome (Long id){
        motorhomeRepository.deleteById(id);
    }

    public Motorhome getMotorhomeById (Long id){
        return motorhomeRepository.findById(id).orElse(null);
    }

    public boolean existMotorhome (Long id){
        return motorhomeRepository.existsById(id);
    }

    public List<Long> motorhomeListById (){
        List<Long> motorhomeListOnlyId = new ArrayList<>();
        for (Motorhome temp : motorhomeListWithStatusTrue()) {
            motorhomeListOnlyId.add(temp.getId());
        }
        return motorhomeListOnlyId;
    }

}
