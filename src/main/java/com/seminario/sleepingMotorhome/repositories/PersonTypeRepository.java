package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTypeRepository extends CrudRepository <PersonType, Long> {

}
