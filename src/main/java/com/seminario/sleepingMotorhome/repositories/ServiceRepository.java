package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository <Service, Long> {

    @Query(value = "SELECT COUNT(t.id) FROM task t, service s WHERE t.service_id = s.id AND t.service_id = ?1", nativeQuery = true)
    int numberOfTask (Long servideId);

    @Query(value = "SELECT SUM(t.is_active) FROM task t, service s WHERE t.service_id = s.id AND t.service_id = ?1", nativeQuery = true)
    int getTasksInFinishedState (Long servideId);
}
