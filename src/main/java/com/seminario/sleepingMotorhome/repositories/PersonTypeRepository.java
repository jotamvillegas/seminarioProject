package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.PersonType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTypeRepository extends CrudRepository <PersonType, Long> {

}
