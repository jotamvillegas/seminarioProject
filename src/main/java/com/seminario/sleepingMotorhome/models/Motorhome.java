package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    private LocalDate dateOfAdmission;

    private LocalDate dateOfEgress;

    @Column
    private Integer rentalDays;

    @Column
    private Integer isActive;

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

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        LocalDate date = LocalDate.parse(dateOfAdmission);
        this.dateOfAdmission = date;
    }

    public LocalDate getDateOfEgress() {
        return dateOfEgress;
    }

    public void setDateOfEgress(LocalDate dateOfEgress) {
        this.dateOfEgress = dateOfEgress;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}
