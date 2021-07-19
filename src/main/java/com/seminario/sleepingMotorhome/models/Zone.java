package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    private String zoneName;

    private String motorhomeType;

    private Integer motorhomeAmount;

    private Integer garageAmount;

    private double garageWidth;

    private double garageDeep;

    @ManyToMany
    @JoinTable(name = "rel_zone_employee"
            , joinColumns = @JoinColumn(name = "zone_id")
            , inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employee;

    @OneToMany(mappedBy = "zone")
    private Set<Garage> garage;

    // constructor
    public Zone() {
        super();
    }
}
