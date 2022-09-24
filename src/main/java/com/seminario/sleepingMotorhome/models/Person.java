package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(length = 70)
    @NotBlank(message = "Campo obligatorio!... Por favor, ingrese un nombre de usuario.")
    private String userName;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Campo obligatorio!... Por favor, ingrese una contraseña.")
    private String password;

    @Column(length = 50)
    @NotBlank(message = "Campo obligatorio!... Por favor, ingrese un nombre.")
    private String name;

    @Column(length = 50)
    @NotBlank(message = "Campo obligatorio!... Por favor, ingrese un apellido.")
    private String surname;

    private Integer documentNumber;

    @Column(length = 70)
    private String addressName;

    private Integer addressNumber;

    @Column(length = 10)
    private String floor;

    @NotNull(message = "Campo obligatorio!... Por favor, ingrese un número de contacto.")
    private Integer phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z", timezone="UTC")
    private Date dateOfAdmission;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z", timezone="UTC")
    private Date dateOfEgress;

    //relations
    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "person_type_id")
    private PersonType personType;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "status_rol_id")
    @JsonIgnoreProperties("person")
    private StatusRol statusRol;


    // constructors

    public Person(){
        super();
    }

    // setters and getters

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Integer addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public StatusRol getStatusRol() {
        return statusRol;
    }

    public void setStatusRol(StatusRol statusRol) {
        this.statusRol = statusRol;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", documentNumber=" + documentNumber +
                ", addressName='" + addressName + '\'' +
                ", addressNumber=" + addressNumber +
                ", floor='" + floor + '\'' +
                ", phone=" + phone +
                ", dateOfAdmission=" + dateOfAdmission +
                ", dateOfEgress=" + dateOfEgress +
                ", personType=" + personType +
                ", statusRol=" + statusRol +
                '}';
    }
}
