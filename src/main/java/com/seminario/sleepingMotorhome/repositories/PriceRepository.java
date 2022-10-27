package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Price;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository <Price, Long> {

}
