package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "motorhome")
public class Motorhome {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column
    //@NotEmpty(message = "Campo obligatorio!...Por favor, ingrese la patente del vehículo.")
    private String enrollment;

    @Column
    private double widthMotorhome;

    @Column
    private double lengthMotorhome;

    //@NotNull(message = "Campo obligatorio!...Por favor, ingrese una fecha.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdmission;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEgress;

    //@NotNull(message = "Campo obligatorio!...Por favor, ingrese los dias de alquiler.")
    @Column
    private Integer rentalDays;

    @Column
    private Integer isActive;

    // relations

    //@NotNull(message = "Campo obligatorio!...Por favor, seleccionar el tipo de vehículo.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorhome_type_id")
    @JsonIgnoreProperties("motorhome")
    private MotorhomeType motorhomeType;

    //@NotNull(message = "Campo obligatorio!...Por favor, seleccionar al dueño del vehículo.")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //@NotNull(message = "Campo obligatorio!...Por favor, seleccionar el garage para el vehículo.")
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
