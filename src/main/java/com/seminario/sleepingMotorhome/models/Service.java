package com.seminario.sleepingMotorhome.models;

import javax.persistence.*;

@Entity
@Table(name = "SERVICE")
public class Service {

    @Id
    @GeneratedValue
    private Long id;
    private String servicesType;
    private String descriptionService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage")
    private Garage garage;

    public Service() {
        super();
    }
}
