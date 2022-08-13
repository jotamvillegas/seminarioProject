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

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional  // con esta notación funciona el LAZY trayendo person type
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person appUser = personRepository.findByUserName(username);

        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority role = new SimpleGrantedAuthority(appUser.getPersonType().getType());
        grantList.add(role);

        UserDetails user = (UserDetails) new User(appUser.getUserName(), appUser.getPassword(), grantList);

        return user;
    }

}
