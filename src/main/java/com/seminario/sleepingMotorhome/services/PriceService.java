package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Price;
import com.seminario.sleepingMotorhome.repositories.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public List<Price> getAllPrice (){
        return (List<Price>) priceRepository.findAll();
    }

    public Price getPrice (Long id){
        return priceRepository.findById(id).orElse(null);
    }

}
