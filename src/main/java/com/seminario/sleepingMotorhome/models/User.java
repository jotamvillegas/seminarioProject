package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends Person {

    // los ids heredan de person

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private Set<Motorhome> motorhome;

    // contructors

    public User(){
        super();
    }

    // setter and getters

    public Set<Motorhome> getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(Set<Motorhome> motorhome) {
        this.motorhome = motorhome;
    }
}
