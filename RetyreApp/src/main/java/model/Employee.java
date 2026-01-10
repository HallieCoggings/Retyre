package model;

import jakarta.persistence.*;
import utils.StringUtils;
import model.enums.Speciality;

import java.util.HashSet;
import java.util.Set;

/**
 * <h1>Employee</h1>
 * represents a garage's employee
 */
@Entity
@DiscriminatorValue("Employee")
public class Employee extends Person{
    /* ------- PARAMETERS ------- */
    @Column(name="SPE")
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @OneToMany(mappedBy = "employee")
    private Set<Intervention> realised;

    /* ------- CONSTRUCTOR ------- */
    public Employee(){

    }

    public Employee(String name, String fName, Speciality spe) {
        super(name,fName);
        this.speciality = spe;
        this.realised = new HashSet<>();
    }

    /* ------- GETTER & SETTER ------- */

    /* ------- METHODS ------- */

    /* ------- MAIN ------- */
    static void main() {

    }
}
