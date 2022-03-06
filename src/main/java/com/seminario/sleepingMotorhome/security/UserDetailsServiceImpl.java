package com.seminario.sleepingMotorhome.security;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person appUser = personRepository.findByUserName(username);

        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority role1 = new SimpleGrantedAuthority("ADMIN");
        grantList.add(role1);

        UserDetails user = (UserDetails) new User(username, appUser.getPassword(), grantList);

        return user;
    }

}
