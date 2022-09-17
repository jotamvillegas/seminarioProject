package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Motorhome;
import com.seminario.sleepingMotorhome.services.MotorhomeService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorhomeRepository extends CrudRepository <Motorhome, Long> {

    @Query(value = "SELECT * FROM motorhome m, garage g WHERE m.garage_id = g.id AND g.garage_status = ?1", nativeQuery = true)
    List<Motorhome> motorhomesListWithStatusTrue (boolean garageStatus);

    @Query(value = "SELECT * FROM motorhome m WHERE is_active = ?1", nativeQuery = true)
    List<Motorhome> motorhomesListActived (int statusIsActive);

    List<Motorhome> findByUserId (Long id);

    @Query(value = "SELECT * FROM motorhome m WHERE m.is_active = 1 AND m.user_id = ?1", nativeQuery = true)
    List<Motorhome> findMotorhomesActiveByUserId(Long id);
}
