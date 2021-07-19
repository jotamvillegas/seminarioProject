package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.StatusRol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRolRepository extends CrudRepository <StatusRol, Long> {

    @Query(value = "SELECT * FROM status_rol sr WHERE sr.status = ?1", nativeQuery = true)
    StatusRol getByStatusRol (String type);

    @Query( value = "SELECT COUNT(sr.status) FROM status_rol sr WHERE sr.status = ?1", nativeQuery = true )
    int countStatusRol( String tagName );

    default boolean existsRolByName( String tagName ) {
        return countStatusRol( tagName ) > 0;
    }

}
