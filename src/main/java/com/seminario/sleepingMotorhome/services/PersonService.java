package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.*;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PersonService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PersonTypeService personTypeService;
    @Autowired
    private StatusRolService statusRolService;


    public List<Person> listPerson(){
        return (List<Person>) personRepository.findAll();
    }

    public void savePerson(Person person, PersonType type){
        if (person.getId() == null) {
            if (type.getType().equals("ADMIN"))     adminService.save(createNewAdmin(person, type));
            if (type.getType().equals("USER"))      userService.save(createNewUser(person, type));
            if (type.getType().equals("EMPLOYEE"))  employeeService.save(createNewEmployee(person, type));
        } else {
            if (type.getType().equals("ADMIN"))     adminService.save(editAdminExisting(person, type));
            if (type.getType().equals("USER"))      userService.save(editUserExisting(person, type));
            if (type.getType().equals("EMPLOYEE"))  employeeService.save(editEmployeeExisting(person, type));
        }
    }

    public void deletePerson(Long personId){
        Person person = personRepository.findById(personId).orElse(null);
        personRepository.delete(person);
    }

    public Person getPersonById (Long personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public Person getPerson (Person person){
        return personRepository.findById(person.getId()).orElse(null);
    }

    public List<Person> onlyUserList (){
        //return personRepository.personListByPersonTypeUser();
        return personRepository.findAllByPersonTypeEquals(3);
    }

    public Long getLastPersonSaved (){
        return personRepository.getLastRegister();
    }

    public StatusRol searchStatusRol (String status){
        return statusRolService.getStatusRol(status);
    }

    public boolean existUsername(String username){
        return personRepository.existsPersonByUserName(username);
    }

    private Admin createNewAdmin (Person person, PersonType type){
        Admin admin = new Admin();
        if (lessThanThirtyCharacters(person.getPassword()))
            admin.setPassword(person.setPassword(convertToBCryptPassword(person.getPassword())));
        admin.setUserName(person.getUserName());
        admin.setName(person.getName());
        admin.setSurname(person.getSurname());
        admin.setDocumentNumber(person.getDocumentNumber());
        admin.setAddressName(person.getAddressName());
        admin.setAddressNumber(person.getAddressNumber());
        admin.setFloor(person.getFloor());
        admin.setPhone(person.getPhone());
        admin.setDateOfAdmission(new Date());
        admin.setDateOfEgress(person.getDateOfEgress());
        admin.setPersonType(type);
        admin.setStatusRol(searchStatusRol("Active"));
        return admin;
    }

    private Admin editAdminExisting (Person person, PersonType type){
        Admin admin;
        admin = adminService.getAdminById(person.getId());
        if (lessThanThirtyCharacters(person.getPassword()))
            admin.setPassword(person.setPassword(convertToBCryptPassword(person.getPassword())));
        admin.setUserName(person.getUserName());
        admin.setName(person.getName());
        admin.setSurname(person.getSurname());
        admin.setDocumentNumber(person.getDocumentNumber());
        admin.setAddressName(person.getAddressName());
        admin.setAddressNumber(person.getAddressNumber());
        admin.setFloor(person.getFloor());
        admin.setPhone(person.getPhone());
        admin.setDateOfEgress(person.getDateOfEgress());
        admin.setPersonType(type);
        admin.setStatusRol(searchStatusRol("Active"));
        return admin;
    }

    private User createNewUser (Person person, PersonType type){
        User user = new User();
        if (lessThanThirtyCharacters(person.getPassword()))
            user.setPassword(person.setPassword(convertToBCryptPassword(person.getPassword())));
        user.setUserName(person.getUserName());
        user.setName(person.getName());
        user.setSurname(person.getSurname());
        user.setDocumentNumber(person.getDocumentNumber());
        user.setAddressName(person.getAddressName());
        user.setAddressNumber(person.getAddressNumber());
        user.setFloor(person.getFloor());
        user.setPhone(person.getPhone());
        user.setDateOfAdmission(new Date());
        user.setDateOfEgress(person.getDateOfEgress());
        user.setPersonType(type);
        user.setStatusRol(searchStatusRol("Active"));
        return user;
    }

    private User editUserExisting (Person person, PersonType type){
        User user;
        user = userService.getUserById(person.getId());
        if (lessThanThirtyCharacters(person.getPassword()))
            user.setPassword(person.setPassword(convertToBCryptPassword(person.getPassword())));
        user.setUserName(person.getUserName());
        user.setName(person.getName());
        user.setSurname(person.getSurname());
        user.setDocumentNumber(person.getDocumentNumber());
        user.setAddressName(person.getAddressName());
        user.setAddressNumber(person.getAddressNumber());
        user.setFloor(person.getFloor());
        user.setPhone(person.getPhone());
        user.setDateOfEgress(person.getDateOfEgress());
        user.setPersonType(type);
        user.setStatusRol(searchStatusRol("Active"));
        return user;
    }

    private Employee createNewEmployee (Person person, PersonType type){
        Employee employee = new Employee();
        if (lessThanThirtyCharacters(person.getPassword()))
            employee.setPassword(person.setPassword(convertToBCryptPassword(person.getPassword())));
        employee.setUserName(person.getUserName());
        employee.setName(person.getName());
        employee.setSurname(person.getSurname());
        employee.setDocumentNumber(person.getDocumentNumber());
        employee.setAddressName(person.getAddressName());
        employee.setAddressNumber(person.getAddressNumber());
        employee.setFloor(person.getFloor());
        employee.setPhone(person.getPhone());
        employee.setDateOfAdmission(new Date());
        employee.setDateOfEgress(person.getDateOfEgress());
        employee.setPersonType(type);
        employee.setStatusRol(searchStatusRol("Active"));
        return employee;
    }

    private Employee editEmployeeExisting (Person person, PersonType type){
        Employee employee;
        employee = employeeService.getEmployeeById(person.getId());
        if (lessThanThirtyCharacters(person.getPassword()))
            employee.setPassword(person.setPassword(convertToBCryptPassword(person.getPassword())));
        employee.setUserName(person.getUserName());
        employee.setName(person.getName());
        employee.setSurname(person.getSurname());
        employee.setDocumentNumber(person.getDocumentNumber());
        employee.setAddressName(person.getAddressName());
        employee.setAddressNumber(person.getAddressNumber());
        employee.setFloor(person.getFloor());
        employee.setPhone(person.getPhone());
        employee.setDateOfEgress(person.getDateOfEgress());
        employee.setPersonType(type);
        employee.setStatusRol(searchStatusRol("Active"));
        return employee;
    }

    public boolean typeIsDifferent (Person person, PersonType type){
        Person per = getPersonById(person.getId());
        if (!per.getPersonType().equals(type)) return true;
        return false;
    }

    private boolean lessThanThirtyCharacters (String pass){
        return pass.length() < 20;
    }

    private String convertToBCryptPassword (String pass){
        return bCryptPasswordEncoder.encode(pass);
    }


}
