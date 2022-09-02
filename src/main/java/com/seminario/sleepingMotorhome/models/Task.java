package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Integer amountHoursWeekly;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss Z", timezone="UTC")
    private Date dateOfAdmission;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss Z", timezone="UTC")
    private Date dateOfEgress;

    // relations

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage")
    private Garage garage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type_id")
    @JsonIgnoreProperties("task")
    private ServiceType serviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    @JsonIgnoreProperties("task")
    private Service service;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "rel_task_employee",
               joinColumns = {@JoinColumn(name = "task_id")},
               inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private Set<Employee> employees;


    // CONSTRUCTOR - GETTERS AND SETTERS

    public Task() {
        super();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmountHoursWeekly() {
        return amountHoursWeekly;
    }

    public void setAmountHoursWeekly(Integer amountHoursWeekly) {
        this.amountHoursWeekly = amountHoursWeekly;
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

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
