package com.udacity.jdnd.course3.critter.entity;


import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
    * Deklaration Variablen
     */
    @Nationalized
    @Column(length = 64)
    private String name;
    private String phoneNumber;
    private String notes;

    /*
     * Eins zu vielen Mapping auf Besitzer
     */
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Pet> pets;

    /*
     * Tier einfügen
     */

    public void addPet(Pet pet) {
        if(this.pets == null){
            this.pets = new ArrayList<>();
        }

        this.pets.add(pet);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
