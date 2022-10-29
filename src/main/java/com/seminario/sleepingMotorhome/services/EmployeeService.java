package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Employee;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    private StatusRolService statusRolService;
    @Autowired
    private PersonTypeService personTypeService;


    public List<Employee> getAll (){
        return (List<Employee>) employeeRepository.findAll();
    }

    @Transactional
    public void save(Employee employee) {
        Employee e;
        if (employee.getId() == null) {
            e = new Employee();
            e.setDateOfAdmission(new Date());
        }
        else {
            e = getEmployeeById(employee.getId());
        }

        if (lessThanThirtyCharacters(employee.getPassword()))
            e.setPassword(convertToBCryptPassword(employee.getPassword()));
        e.setUserName(employee.getUserName());
        e.setName(employee.getName());
        e.setSurname(employee.getSurname());
        e.setDocumentNumber(employee.getDocumentNumber());
        e.setAddressName(employee.getAddressName());
        e.setAddressNumber(employee.getAddressNumber());
        e.setFloor(employee.getFloor());
        e.setPhone(employee.getPhone());
        e.setDateOfEgress(employee.getDateOfEgress());
        e.setPersonType(personTypeService.getPersonType("EMPLOYEE"));
        e.setStatusRol(searchStatusRol("Active"));

        employeeRepository.save(e);
    }

    public boolean delete(Long employeeId){
        if (employeeRepository.getCantTaskByEmployee(employeeId) == 0) {
            Employee employee = employeeRepository.findById(employeeId).orElse(null);
            employeeRepository.delete(employee);
            return true;
        }
        return false;
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee getEmployeeByUserName(String username){
        return employeeRepository.findByUserName(username);
    }

    public boolean existEmployee (String username){
        return employeeRepository.existsEmployeeByUserName(username);
    }

    public StatusRol searchStatusRol (String status){
        return statusRolService.getStatusRol(status);
    }

    private boolean lessThanThirtyCharacters (String pass){
        return pass.length() < 20;
    }

    private String convertToBCryptPassword (String pass){
        return bCryptPasswordEncoder.encode(pass);
    }


}
