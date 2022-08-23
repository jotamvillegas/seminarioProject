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
        if (employee.getId() == null)
            employeeRepository.save(createNewEmployee(employee));
        else
            employeeRepository.save(editEmployeeExisting(employee));
    }

    private Employee createNewEmployee (Employee employee){
        Employee e = new Employee();
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
        e.setDateOfAdmission(new Date());
        e.setDateOfEgress(employee.getDateOfEgress());
        e.setPersonType(personTypeService.getPersonType("EMPLOYEE"));
        e.setStatusRol(searchStatusRol("Active"));
        return e;
    }

    private Employee editEmployeeExisting (Employee employee){
        Employee e;
        e = getEmployeeById(employee.getId());
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
        return e;
    }

    public void delete(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        employeeRepository.delete(employee);
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
