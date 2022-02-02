package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Person {

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Motorhome> motorhome;

    // contructors

    public User (){
        super();
    }

    // setter and getters

    /*@Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }*/

    public Set<Motorhome> getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(Set<Motorhome> motorhome) {
        this.motorhome = motorhome;
    }
}
