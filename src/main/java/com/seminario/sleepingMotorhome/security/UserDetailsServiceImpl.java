package com.seminario.sleepingMotorhome.security;

import com.seminario.sleepingMotorhome.models.Person;
import com.seminario.sleepingMotorhome.models.PersonType;
import com.seminario.sleepingMotorhome.repositories.PersonRepository;
import com.seminario.sleepingMotorhome.repositories.PersonTypeRepository;
import com.seminario.sleepingMotorhome.services.PersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional  // con esta notación funciona el LAZY trayendo person type
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonTypeRepository personTypeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Person appUser = personRepository.findByUserName(username);

        Set<GrantedAuthority> grantList = new HashSet<>();
        GrantedAuthority role1 = new SimpleGrantedAuthority(appUser.getPersonType().getType());
        grantList.add(role1);

        UserDetails user = (UserDetails) new User(username, appUser.getPassword(), grantList);

        return user;
    }

}
