package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {

    /*
     * Deklaration Variablen
     */

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    private Set<EmployeeSkill> skills;

    @ElementCollection
    private Set<DayOfWeek> daysAvailability;

    private String name;


    /*
     * Getter und Setter Methoden
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailability() {
        return daysAvailability;
    }

    public void setDaysAvailability(Set<DayOfWeek> daysAvailable) {
        this.daysAvailability = daysAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
