package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status_rol")
public class StatusRol {

    @Id
    @GeneratedValue
    private Long id;

    private String status;

    @OneToMany(mappedBy = "statusRol")
    @JsonIgnoreProperties( "statusRol" )
    @JsonInclude( JsonInclude.Include.NON_NULL )
    private Set<Person> person;

    // constructors

    public StatusRol() {
        super();
    }

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Person> getPerson() {
        return person;
    }

    public void setPerson(Set<Person> person) {
        this.person = person;
    }
}
