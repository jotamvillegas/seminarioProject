package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;

@Entity
@Table(name = "MOTORHOME_TYPE")
public class MotorhomeType {

    @Id
    @GeneratedValue
    private Long id;
    private String motorhomeType;
    private String descriptionType;

    @OneToOne(mappedBy = "motorhomeType")
    private Motorhome motorhome;


    public MotorhomeType() {
        super();
    }
}
