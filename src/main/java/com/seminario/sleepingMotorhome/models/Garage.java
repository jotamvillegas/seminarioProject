package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Integer garageNumber;

    private Date dateOfAdmission;

    private Date dateOfEgress;

    private boolean garageStatus;

    // Relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    @JsonIgnoreProperties("zone")
    private Zone zone;

    @OneToMany(mappedBy = "garage")
    private Set<Task> service;

    @OneToOne(mappedBy = "garage")
    private Motorhome motorhome;

    // Constructor
    public Garage (){
        super();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGarageNumber() {
        return garageNumber;
    }

    public void setGarageNumber(Integer garageNumber) {
        this.garageNumber = garageNumber;
    }

    public Date getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(Date dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    public Date getDateOfEgress() {
        return dateOfEgress;
    }

    public void setDateOfEgress(Date dateOfEgress) {
        this.dateOfEgress = dateOfEgress;
    }

    public boolean isGarageStatus() {
        return garageStatus;
    }

    public void setGarageStatus(boolean garageStatus) {
        this.garageStatus = garageStatus;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Set<Task> getService() {
        return service;
    }

    public void setService(Set<Task> service) {
        this.service = service;
    }

    public Motorhome getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(Motorhome motorhome) {
        this.motorhome = motorhome;
    }
}
