package model;

import jakarta.persistence.*;
import utils.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * <h1>Vehicle</h1>
 * Represents a vehicle with it information
 */

@Entity
public class Vehicle {
    /* ------- PARAMETERS ------- */
    @Id
    @Column(name="licencePlate",
            nullable = false,
            length = 15)
    private String licencePlate;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "FK_TypeV")
    private VehicleType vType;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="FK_Owner")
    private Owner owner;

    @Column(name = "Mileage", columnDefinition = "NUMERIC(10,2)")
    private float mileage;

    @Column(name= "CirculationDate")
    private Date dateCirculation; //from java.util

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Vehicle(){
    }

    //Data
    public Vehicle(String licencePlate, VehicleType vType, Owner owner, float mileage, Date dateCirculation) {
        if (StringUtils.checkString(licencePlate) && vType != null && owner != null && mileage>0 && dateCirculation != null) {
            this.licencePlate = licencePlate;
            this.vType = vType;
            this.owner = owner;
            this.mileage = mileage;
            this.dateCirculation = dateCirculation;
        }
    }




    /* ------- GETTER & SETTER ------- */


    /* ------- METHODS ------- */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(licencePlate, vehicle.licencePlate) && Objects.equals(vType, vehicle.vType) && Objects.equals(owner, vehicle.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licencePlate, vType, owner);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licencePlate='" + licencePlate + '\'' +
                ", vType=" + vType +
                ", owner=" + owner +
                ", mileage=" + mileage +
                ", dateCirculation=" + dateCirculation +
                '}';
    }

    /* ------- MAIN ------- */
    static void main() {

    }

}
