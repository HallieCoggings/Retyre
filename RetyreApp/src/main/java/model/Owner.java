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
@Entity
@DiscriminatorValue("Owner")
public class Owner extends Person{
    /* ------- PARAMETERS ------- */
    @Column(name="Personal",nullable = false,length = 255)
    private String personalDetails; //TODO:Add a class "Details"

    @OneToMany(mappedBy = "owner")
    private Set<Vehicle> vehicles;

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Owner(){
        super();
    }

    //Data
    public Owner(String name, String fName, String pDetails) {
        super(name,fName);
        if (StringUtils.checkString(pDetails)){
            this.personalDetails = pDetails;
            this.vehicles = new HashSet<>();
        }
    }

    /* ------- GETTER & SETTER ------- */

    /* ------- METHODS ------- */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(personalDetails, owner.personalDetails) && Objects.equals(vehicles, owner.vehicles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalDetails, vehicles);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "personalDetails='" + personalDetails + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }

    /* ------- MAIN ------- */
    static void main() {

    }
}
