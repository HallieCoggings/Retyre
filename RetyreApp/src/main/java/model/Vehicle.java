package model;

import jakarta.persistence.*;
import utils.StringUtils;

import java.util.Date;

/**
 * <h1>Vehicle</h1>
 * Represents a vehicle with it information
 */

@Entity
public class Vehicle {
    /* ------- PARAMETERS ------- */
    @Id
    private String licencePlate;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "FK_TypeV")
    private VehicleType vType;

    @OneToOne
    private Owner owner; //TODO : change it to the class "Owner"

    @Column(name = "Mileage", columnDefinition = "NUMERIC(10,2)")
    private float mileage;

    @Column(name= "CirculationDate")
    @Temporal(TemporalType.DATE)
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

    /* ------- MAIN ------- */
    static void main() {

    }

}
