package com.seminario.sleepingMotorhome.repositories;

import com.seminario.sleepingMotorhome.models.ServiceType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends CrudRepository <ServiceType, Long> {

    boolean existsServiceTypeByDescription (String service);

}
