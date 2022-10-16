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

    @Query(value = "SELECT MAX(g.garage_number) FROM garage g WHERE g.zone_id = ?1", nativeQuery = true)
    String getMaxValueGarageNumber(Long zoneId);

    // cantidad de garage por zona
    @Query(value = "SELECT COUNT(*) FROM garage g WHERE g.zone_id = ?1", nativeQuery = true)
    int getCantGarageByZone(Long zoneId);

    // lista de garage por zona
    @Query(value = "SELECT *  FROM garage g WHERE g.zone_id = ?1", nativeQuery = true)
    List<Garage> getListGarageByZone(Long zoneId);

    //@Query(value = "SELECT * FROM garage g WHERE g.garage_status = 0 AND g.zone_id = ?1", nativeQuery = true)
    @Query(value = "SELECT * FROM garage g, `zone` z WHERE g.zone_id = z.id AND g.garage_status = 0 AND z.motorhome_type_id = ?1", nativeQuery = true)
    List<Garage> getAllGarageByStatusFreeAndZone(Long zone);


    @Query(value = "SELECT COUNT(*)  FROM garage g, `zone` z WHERE g.zone_id = z.id AND g.garage_status = 0 AND z.motorhome_type_id = ?1", nativeQuery = true)
    int countGarages (Long motorhomeTypeId);

}
