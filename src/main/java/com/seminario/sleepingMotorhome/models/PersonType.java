package com.seminario.sleepingMotorhome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person_type")
public class PersonType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(length = 50)
    private String Type;

    @OneToMany(mappedBy = "personType")
    @JsonIgnoreProperties("personType")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Person> person;

    // constructors

    public PersonType() {
        super();
    }

    // getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public  Set<Person> getPerson() {
        return person;
    }

    public void setPerson( Set<Person> person) {
        this.person = person;
    }

}
