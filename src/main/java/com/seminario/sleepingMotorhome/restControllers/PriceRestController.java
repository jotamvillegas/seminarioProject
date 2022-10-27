package com.seminario.sleepingMotorhome.restControllers;

import com.seminario.sleepingMotorhome.models.Garage;
import com.seminario.sleepingMotorhome.models.Price;
import com.seminario.sleepingMotorhome.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(path = "/sleepingMotorhome/price", produces = {"application/json"})
public class PriceRestController {

    @Autowired
    private PriceService priceService;

    @GetMapping(path = "/rest-all")
    public @ResponseBody
    List<Price> getAllPrice (){
        return priceService.getAllPrice();
    }

    // buscar garages libres por zona
    @GetMapping(path = "/rest-priceById/{id}")
    public @ResponseBody
    Price getPrice (@PathVariable("id") Long id){
        return priceService.getPrice(id);
    }
}
