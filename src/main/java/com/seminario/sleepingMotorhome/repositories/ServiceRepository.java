package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository <Service, Long> {

    boolean existsServiceByDescription (String service);
}
