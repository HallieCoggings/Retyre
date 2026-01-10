package model;

import jakarta.persistence.*;
import utils.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <h1>Owner</h1>
 * Represents an owner of a vehicle
 */
/*
@Entity
@DiscriminatorValue("Owner")
public class Owner extends Person{
    /* ------- PARAMETERS ------- 
    @Column(name="Personal",nullable = false,length = 255)
    private String personalDetails; //TODO:Add a class "Details"

    @OneToMany(mappedBy = "owner")
    private Set<Vehicle> vehicles;

    /* ------- CONSTRUCTOR -------
    //Default
    public Owner(){
    }

    //Data
    public Owner(String name, String fName, String pDetails) {
        super(name,fName);
        if (StringUtils.checkString(pDetails)){
            this.personalDetails = pDetails;
            this.vehicles = new HashSet<>();
        }
    }

    /* ------- GETTER & SETTER -------

    /* ------- METHODS ------- 
    public boolean addVehicle(Vehicle v){
        if (v==null){return false;}
        if (this.vehicles.contains(v)){return false;}

        this.vehicles.add(v);
        v.setOwner(this);

        return true;
    }

    public boolean delVehicle(Vehicle v){
        if (v==null){return false;}
        if (!this.vehicles.contains(v)){return false;}

        v.setOwner(null);
        this.vehicles.remove(v);
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(personalDetails, owner.personalDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(personalDetails);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "personalDetails='" + personalDetails + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }

    /* ------- MAIN -------
    static void main() {

    }
}
*/


package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>Owner</h1>
 * Represents an owner of one or more vehicles.
 * Inherits from Person
 */
@Entity
@Table(name = "owner")
@DiscriminatorValue("OWNER")
public class Owner extends Person {

    /* ------- PARAMETERS ------- */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles = new HashSet<>();

    /* ------- CONSTRUCTORS ------- */
    public Owner() {
        super();
    }

    public Owner(String name, String firstName) {
        super(name, firstName);
    }

    public Owner(String name, String firstName, LocalDate dateOfBirth, String telephone) {
        super(name, firstName, dateOfBirth, telephone);
    }

    /* ------- GETTERS AND SETTERS ------- */
    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /* ------- METHODS ------- */
    public void addVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            this.vehicles.add(vehicle);
            vehicle.setOwner(this); //TODO: check if name is correct in Vehicle
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicle != null) {
            this.vehicles.remove(vehicle);
            vehicle.setOwner(null);
        }
    }

    @Override
    public String toString() {
        return "Owner{id=" + getId() + ", name='" + getFullName() + "', vehicles=" + vehicles.size() + "}";
    }
}
