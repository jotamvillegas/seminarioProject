package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Zone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends CrudRepository <Zone, Long> {

    @Query(value = "SELECT z.motorhome_type_id FROM `zone` z WHERE z.id = ?1", nativeQuery = true)
    Long getMotorhomeTypeIdByZoneId (Long zoneId);

}
