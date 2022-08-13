package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    public void save (Employee employee){
        employeeRepository.save(employee);
    }

    public List<Employee> getAll (){
        return (List<Employee>) employeeRepository.findAll();
    }

    public void editEmployee (Employee employee){
        employeeRepository.findById(employee.getId());
    }

    public void delete (Employee employee){
        employeeRepository.delete(employee);
    }

    public boolean exist (Long id){
        return employeeRepository.existsById(id);
    }

    public Employee getEmployeeById (Long personId) {
        return employeeRepository.findById(personId).orElse(null);
    }


}
