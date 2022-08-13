package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.models.User;
import com.seminario.sleepingMotorhome.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll (){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean existUser (Long id){
        return userRepository.existsById(id);
    }


}
