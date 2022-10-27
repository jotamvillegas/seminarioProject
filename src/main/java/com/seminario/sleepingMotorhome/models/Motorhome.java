package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "motorhome")
public class Motorhome {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Column
    private String enrollment;

    @Column
    private Double widthMotorhome;

    @Column
    private Double lengthMotorhome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdmission;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEgress;

    @Column
    private Integer rentalDays;

    @Column
    private Integer isActive;

    @Column
    private Double payment;

    @Column
    private Double balance;

    @Column
    private Double total;

    // relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorhome_type_id")
    @JsonIgnoreProperties("motorhome")
    private MotorhomeType motorhomeType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_id")//, referencedColumnName = "id")
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

    public Double getWidthMotorhome() {
        return widthMotorhome;
    }

    public void setWidthMotorhome(Double widthMotorhome) {
        this.widthMotorhome = widthMotorhome;
    }

    public Double getLengthMotorhome() {
        return lengthMotorhome;
    }

    public void setLengthMotorhome(Double lengthMotorhome) {
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

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
