package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /*
     * Vielen zu eins Zuordnung
     */
    @ManyToOne(cascade=CascadeType.ALL, fetch= FetchType.LAZY)
    private Customer customer;
    /*
     * Deklaration Variablen
     */
    @Nationalized
    @Column(length = 64)
    private PetType type;
    private String name;
    private LocalDate birthDate;
    private String notes;

    @ManyToMany
    private List<Schedule> schedules;

    public Pet( PetType type, String name, LocalDate birthDate, Customer customer, String notes) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.customer = customer;
        this.notes = notes;

    }

    public Pet() {

    }

    /*
     * Getter und Setter Methoden
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
