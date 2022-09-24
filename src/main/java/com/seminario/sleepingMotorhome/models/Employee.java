package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee extends Person {

    // los ids heredan de person

    private String specialty;

    @JsonIgnore
    @ManyToMany(mappedBy = "employee")
    private Set<Zone> zones;

    @JsonIgnore
    @ManyToMany(mappedBy = "employees")
    private Set<Task> tasks;

    // contructors

    public Employee() {
        super();
    }

    // setter and getters

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Set<Zone> getZones() {
        return zones;
    }

    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}
