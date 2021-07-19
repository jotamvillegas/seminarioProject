package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;

@Entity
@Table(name = "MOTORHOME")
public class Motorhome {

    @Id
    @GeneratedValue
    private Long id;
    private String enrollment;
    private double widthMotorhome;
    private double lengthMotorhome;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "motorhome")
    private Garage garage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorhome_type")
    private MotorhomeType motorhomeType;

    // constructors

    public Motorhome() {
        super();
    }


    //setters and getters


}
