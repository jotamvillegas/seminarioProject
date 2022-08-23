package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Admin;
import com.seminario.sleepingMotorhome.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository <Admin, Long> {

    Admin findByUserName (String username);

    boolean existsAdminByUserName (String username);

}
