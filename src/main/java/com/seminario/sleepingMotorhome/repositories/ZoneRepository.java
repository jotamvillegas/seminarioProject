package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends CrudRepository <Zone, Long> {

}
