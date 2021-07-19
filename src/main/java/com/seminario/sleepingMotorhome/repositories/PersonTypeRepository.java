package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTypeRepository extends CrudRepository <PersonType, Long> {

    @Query(value = "SELECT * FROM person_type pt WHERE pt.`type` = ?1", nativeQuery = true)
    PersonType getByType (String type);

    @Query( value = "SELECT COUNT(pt.type) FROM person_type pt WHERE pt.`type` = ?1", nativeQuery = true)
    int countPersonType(String tagName);

    default boolean existsByName(String tagName) {
        return countPersonType (tagName) > 0;
    }

}
