package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long> {

    Person findByUserName (String username);

    boolean existsPersonByUserName (String username);

}
