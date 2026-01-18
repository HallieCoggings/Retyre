package model;

import jakarta.persistence.*;
import utils.StringUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "vehicle")
    private Set<Intervention> interventions;

    @Column(name = "Mileage", columnDefinition = "NUMERIC(10,2)")
    private int mileage;

    @Column(name= "CirculationDate")
    private LocalDate dateCirculation; //from java.util

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Vehicle(){
    }

    //Data
    public Vehicle(String licencePlate, VehicleType vType, Owner owner, int mileage, LocalDate dateCirculation) {
        if (StringUtils.checkString(licencePlate) && vType != null && owner != null && mileage>0 && dateCirculation != null) {
            this.licencePlate = licencePlate.toUpperCase();
            this.mileage = mileage;
            this.dateCirculation = dateCirculation;
            this.interventions = new HashSet<>();
        }
    }




    /* ------- GETTER & SETTER ------- */
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public VehicleType getvType() {
        return vType;
    }

    public void setvType(VehicleType vType) {
        this.vType = vType;
    }

    public float getMileage() {
        return mileage;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    /* ------- METHODS ------- */
    public boolean addIntervention (Intervention i) {
        if (i==null){return false;}
        if (this.interventions.contains(i)){return false;}
        this.interventions.add(i);
        return true;
    }

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
