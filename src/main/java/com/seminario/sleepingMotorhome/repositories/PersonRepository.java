package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository <Person, Long> {

    Person findByUserName (String username);

    boolean existsPersonByUserName (String username);

    //@Query(value = "SELECT p FROM person p WHERE p.person_type_id = 3", nativeQuery = true)
    //List<Person> personListByPersonTypeUser();

    List<Person> findAllByPersonTypeEquals (int personType);

    @Query(value = "SELECT MAX(id)  FROM person", nativeQuery = true)
    Long getLastRegister();

}
