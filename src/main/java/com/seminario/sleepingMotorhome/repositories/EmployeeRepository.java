package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository <Employee, Long> {

    Employee findByUserName (String username);

    boolean existsEmployeeByUserName (String username);

    @Query(value = "SELECT COUNT(*) FROM task t , rel_task_employee rte, employee e WHERE t.id = rte.task_id AND rte.employee_id = e.id AND e.id = ?1", nativeQuery = true)
    int getCantTaskByEmployee(Long employeeId);

}
