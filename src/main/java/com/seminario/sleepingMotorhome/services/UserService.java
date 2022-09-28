package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Motorhome;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

    @Autowired
    private PersonTypeService personTypeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRolService statusRolService;
    @Autowired
    private MotorhomeService motorhomeService;


    public List<User> getAll (){
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        User u;
        if (user.getId() == null) {
            u = new User();
            u.setDateOfAdmission(new Date());
        }
        else {
            u = getUserById(user.getId());
        }

        if (lessThanThirtyCharacters(user.getPassword()))
            u.setPassword(convertToBCryptPassword(user.getPassword()));
        u.setUserName(user.getUserName());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setDocumentNumber(user.getDocumentNumber());
        u.setAddressName(user.getAddressName());
        u.setAddressNumber(user.getAddressNumber());
        u.setFloor(user.getFloor());
        u.setPhone(user.getPhone());
        u.setDateOfEgress(user.getDateOfEgress());
        u.setPersonType(personTypeService.getPersonType("USER"));
        u.setStatusRol(searchStatusRol("Active"));

        userRepository.save(u);
    }

    public void delete(Long userId){
        List<Motorhome> motorhomeList = motorhomeService.getMotorhomeByUserId(userId) ;
        for (Motorhome mot : motorhomeList) {
            motorhomeService.deleteMotorhome(mot.getId());
        }
        User user = userRepository.findById(userId).orElse(null);
        userRepository.delete(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUserName(String username){
        return userRepository.findByUserName(username);
    }

    public boolean existUser (String username){
        return userRepository.existsUserByUserName(username);
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
