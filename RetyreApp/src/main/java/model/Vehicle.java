package model;

import java.util.Date;

/**
 * <h1>Vehicle</h1>
 * Represents a vehicle with it information
 */
public class Vehicle {
    /* ------- PARAMETERS ------- */
    private VehicleType vType;
    private String owner; //TODO : change it to the class "Owner"
    private float mileage;
    private Date dateCirculation; //TODO : check if good LibImport (sql or util)

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Vehicle(){

    }

    //Data

    public Vehicle(VehicleType vType, String owner, float mileage, Date dateCirculation) {
        if (vType != null && owner!=null && mileage>= 0 && dateCirculation!=null) {
            this.vType = vType;
            this.owner = owner;
            this.mileage = mileage;
            this.dateCirculation = dateCirculation;
        }else{
            return; //Better to throw an exception
        }
    }


    /* ------- GETTER & SETTER ------- */


    /* ------- METHODS ------- */

    /* ------- MAIN ------- */
    static void main() {

    }

}
