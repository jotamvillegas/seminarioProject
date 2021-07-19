package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "GARAGE")
public class Garage {

    @Id
    @GeneratedValue
    private Long id;

    private Integer garageNumber;

    private Date dateOfAdmission;

    private Date dateOfEgress;

    private boolean garageStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone", nullable = false)
    private Zone zone;

    @OneToMany(mappedBy = "garage")
    private Set<Service> service;

    @OneToOne
    @JoinColumn(name = "motorhome_id")
    private Motorhome motorhome;

    public Garage (){
        super();
    }

}
