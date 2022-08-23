package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Long> {

    Employee findByUserName (String username);

    boolean existsEmployeeByUserName (String username);

}
