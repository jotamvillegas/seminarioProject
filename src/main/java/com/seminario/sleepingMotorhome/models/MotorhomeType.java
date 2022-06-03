package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "MOTORHOME_TYPE")
public class MotorhomeType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column
    @NotBlank
    private String motorhomeType;

    //relations

    @OneToMany(mappedBy = "motorhomeType")
    @JsonIgnoreProperties("motorhomeType")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Motorhome> motorhome;

    // constructors
    public MotorhomeType() {
        super();
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotorhomeType() {
        return motorhomeType;
    }

    public void setMotorhomeType(String motorhomeType) {
        this.motorhomeType = motorhomeType;
    }

    public Set<Motorhome> getMotorhome() {
        return motorhome;
    }

    public void setMotorhome(Set<Motorhome> motorhome) {
        this.motorhome = motorhome;
    }
}
