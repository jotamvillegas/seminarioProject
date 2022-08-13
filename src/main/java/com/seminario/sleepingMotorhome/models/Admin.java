package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin extends Person {

    // los ids heredan de person

    private String sueldo;

    // contructors

    public Admin(){
        super();
    }

    // Getters and Setters

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

}
