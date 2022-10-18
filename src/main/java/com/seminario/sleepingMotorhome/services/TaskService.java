package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.repositories.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeService employeeService;


    public List<Task> getAll (){
        return (List<Task>) taskRepository.findAll();
    }

    @Transactional
    public void save (Task task, Person person, @NotNull com.seminario.sleepingMotorhome.models.Service service, Garage garage, ServiceType serviceType){
        Task t;
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add((Employee) person);

        if (task.getId() == null) {
            t = new Task();
            t.setDateOfAdmission(new Date());
        }
        else {
            t = getTaskById(task.getId());
        }

        t.setAmountHoursWeekly(task.getAmountHoursWeekly());
        t.setDateOfEgress(null);
        t.setDescription(null);
        t.setGarage(garage);
        t.setServiceType(serviceType);
        t.setService(service);
        t.setEmployees(employeeSet);

        taskRepository.save(t);
    }

    public void delete (Long id){
        taskRepository.deleteById(id);
    }

    public Task getTaskById (Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public boolean existService (Long id){
        return taskRepository.existsById(id);
    }

    public List<Task> getServiceByEmployee (Employee employee){
        return taskRepository.findAllByEmployees(employee);
    }

    public List<Task> getTasksByGarage (Long garageId){
        return taskRepository.getListTaskByGarage(garageId);
    }

}
