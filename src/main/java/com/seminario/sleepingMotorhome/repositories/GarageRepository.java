package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Garage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends CrudRepository <Garage, Long> {

    @Query(value = "SELECT * FROM garage g WHERE garage_status = ?1", nativeQuery = true)
    List<Garage> findAllByGarageStatus(boolean garageStatus);


}
