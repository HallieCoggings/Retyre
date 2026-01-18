package model;

import jakarta.persistence.*;
import utils.StringUtils;
import model.enums.Speciality;

import java.util.HashSet;
import java.util.Objects;
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
    public String getFullName (){
        return this.getName() + " " + this.getFirstName();
    }

    /* ------- METHODS ------- */
    public boolean addIntervention (Intervention i){
        if (i==null){return false;}
        if (this.realised.contains(i)){return false;}
        this.realised.add(i);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return speciality == employee.speciality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speciality);
    }

    /* ------- MAIN ------- */
    static void main() {

    }
}
