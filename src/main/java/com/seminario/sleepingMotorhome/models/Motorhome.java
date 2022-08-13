package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "motorhome")
public class Motorhome {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column
    @NotEmpty(message = "Campo obligatorio!...Por favor, ingrese la patente del vehículo.")
    private String enrollment;

    @Column
    private double widthMotorhome;

    @Column
    private double lengthMotorhome;

    // relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorhome_type_id")
    @JsonIgnoreProperties("motorhome")
    private MotorhomeType motorhomeType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id", referencedColumnName = "id")
    private Garage garage;


    // constructors

    public Motorhome() {
        super();
    }


    //setters and getters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public double getWidthMotorhome() {
        return widthMotorhome;
    }

    public void setWidthMotorhome(double widthMotorhome) {
        this.widthMotorhome = widthMotorhome;
    }

    public double getLengthMotorhome() {
        return lengthMotorhome;
    }

    public void setLengthMotorhome(double lengthMotorhome) {
        this.lengthMotorhome = lengthMotorhome;
    }

    public MotorhomeType getMotorhomeType() {
        return motorhomeType;
    }

    public void setMotorhomeType(MotorhomeType motorhomeType) {
        this.motorhomeType = motorhomeType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
