package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "employee_id")
public class Employee extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialty;

    @ManyToMany(mappedBy = "employee")
    private Set<Zone> zones;

    // contructors

    public Employee() {
        super();
    }

    // setter and getters




}
