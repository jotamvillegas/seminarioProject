package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    boolean existsServiceByDescription (String task);

    List<Task> findAllByEmployees (Employee employee);

    @Query(value = "SELECT * FROM task t WHERE t.garage = ?1", nativeQuery = true)
    List<Task> getListTaskByGarage (Long garageId);

}
