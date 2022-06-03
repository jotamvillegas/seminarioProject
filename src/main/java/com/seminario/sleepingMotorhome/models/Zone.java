package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String zoneName;

    private String motorhomeType;

    private Integer motorhomeAmount;

    private Integer garageAmount;

    private double garageWidth;

    private double garageDeep;

    // Relations

    @ManyToMany
    @JoinTable(name = "rel_zone_employee"
            , joinColumns = @JoinColumn(name = "zone_id")
            , inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employee;

    @OneToMany(mappedBy = "zone")
    @JsonIgnoreProperties("zone")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Garage> garage;

    // constructor

    public Zone() {
        super();
    }

    public Long getId() {
        return id;
    }

    // Getters and Setters

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getMotorhomeType() {
        return motorhomeType;
    }

    public void setMotorhomeType(String motorhomeType) {
        this.motorhomeType = motorhomeType;
    }

    public Integer getMotorhomeAmount() {
        return motorhomeAmount;
    }

    public void setMotorhomeAmount(Integer motorhomeAmount) {
        this.motorhomeAmount = motorhomeAmount;
    }

    public Integer getGarageAmount() {
        return garageAmount;
    }

    public void setGarageAmount(Integer garageAmount) {
        this.garageAmount = garageAmount;
    }

    public double getGarageWidth() {
        return garageWidth;
    }

    public void setGarageWidth(double garageWidth) {
        this.garageWidth = garageWidth;
    }

    public double getGarageDeep() {
        return garageDeep;
    }

    public void setGarageDeep(double garageDeep) {
        this.garageDeep = garageDeep;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public Set<Garage> getGarage() {
        return garage;
    }

    public void setGarage(Set<Garage> garage) {
        this.garage = garage;
    }
}
