package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    public void createEmployee (Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> listEmployee (){
        return (List<Employee>) employeeRepository.findAll();
    }

    public void editEmployee (Employee employee){
        employeeRepository.findById(employee.getId());
    }

    public void deleteEmployee (Employee employee){
        employeeRepository.delete(employee);
    }

    public boolean existEmployee (Long id){
        return employeeRepository.existsById(id);
    }


}
