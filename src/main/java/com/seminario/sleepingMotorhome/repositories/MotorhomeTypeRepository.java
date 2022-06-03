package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.MotorhomeType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorhomeTypeRepository extends CrudRepository <MotorhomeType, Long> {

}
