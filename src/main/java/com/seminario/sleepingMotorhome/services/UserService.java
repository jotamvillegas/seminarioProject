package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.models.StatusRol;
import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import com.seminario.sleepingMotorhome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> userList (){
        return (List<User>) userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean existUser (Long id){
        return userRepository.existsById(id);
    }
}
