package com.seminario.sleepingMotorhome.security;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import com.seminario.sleepingMotorhome.repositories.PersonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional  // con esta notación funciona el LAZY trayendo person type
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired private PersonRepository personRepository;
    @Autowired private PersonTypeRepository personTypeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person appUser = personRepository.findByUserName(username);

       /* List<String> listRoles = new ArrayList<>();
        if (appUser.getPersonType().getType().equals("ADMIN")){
            listRoles.add("ADMIN");
            listRoles.add("EMPLOYEE");
            listRoles.add("USER");
        }
        if (appUser.getPersonType().getType().equals("EMPLOYEE")){
            listRoles.add("EMPLOYEE");
            listRoles.add("USER");
        }
        if (appUser.getPersonType().getType().equals("USER")){
            listRoles.add("USER");
        }

        Set<GrantedAuthority> grantList = new HashSet<>();
        for (String temp : listRoles){
            grantList.add(new SimpleGrantedAuthority(temp.toUpperCase()));
        }*/

        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority role = new SimpleGrantedAuthority(appUser.getPersonType().getType());
        grantList.add(role);

        UserDetails user = (UserDetails) new User(appUser.getUserName(), appUser.getPassword(), grantList);

        return user;
    }



}
