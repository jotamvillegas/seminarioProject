package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository <Admin, Long> {

    boolean existsPersonByUserName (String username);

}
