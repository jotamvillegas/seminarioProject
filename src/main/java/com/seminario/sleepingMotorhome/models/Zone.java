package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @NotBlank(message = "Campo obligatorio!... Por favor, ingrese un nombre o identificador para la zona.")
    private String zoneName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z", timezone="UTC")
    private Date dateOfCreation;

    @NotNull(message = "Campo obligatorio!... Por favor, ingrese una cantidad de garages para la zona.")
    private Integer garageAmount;


    // Relations

    // un tipo de motorhome puede tener varias zonas (Small => [A, D], Medium => [B, E])
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorhome_type_id")
    @JsonIgnoreProperties("zones")
    private MotorhomeType motorhomeType;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "rel_zone_employee"
            , joinColumns = @JoinColumn(name = "zone_id")
            , inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employee;

    @JsonIgnore
    @OneToMany(mappedBy = "zone")
    @JsonIgnoreProperties("zone")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Garage> garage;

    // constructor

    public Zone() {
        super();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /*public String getMotorhomeType() {
        return motorhomeType;
    }

    public void setMotorhomeType(String motorhomeType) {
        this.motorhomeType = motorhomeType;
    }*/

    public MotorhomeType getMotorhomeType() {
        return motorhomeType;
    }

    public void setMotorhomeType(MotorhomeType motorhomeType) {
        this.motorhomeType = motorhomeType;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getGarageAmount() {
        return garageAmount;
    }

    public void setGarageAmount(Integer garageAmount) {
        this.garageAmount = garageAmount;
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
