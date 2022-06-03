package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.MotorhomeType;
import com.seminario.sleepingMotorhome.repositories.MotorhomeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorhomeTypeService {

    @Autowired
    private MotorhomeTypeRepository motorhomeTypeRepository;

    public List<MotorhomeType> motorhomeTypeList (){
        return (List<MotorhomeType>) motorhomeTypeRepository.findAll();
    }

    public void saveMotorhomeType (MotorhomeType motorhomeType){
        motorhomeTypeRepository.save(motorhomeType);
    }

    public void deleteMotorhomeType (Long id){
        motorhomeTypeRepository.deleteById(id);
    }

    public MotorhomeType getMotorhomeType (Long id){
        return motorhomeTypeRepository.findById(id).orElse(null);
    }

    public boolean existMotorhomeType (Long id){
        return motorhomeTypeRepository.existsById(id);
    }

}
