package model;

import jakarta.persistence.*;
import model.enums.EnergyType;
import model.enums.TransmissionType;
import utils.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <h1>VehicleType</h1>
 * Represent a type of vehicle used in the application
 */
@Table(name="VehicleType")
@Entity
public class VehicleType {
    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVehiculeType;

    @Column(name = "Brand",
            nullable = false,
            length = 255)
    private String brand;

    @Column(name = "Model",
            nullable = false,
            length = 100)
    private String model;

    @Column(name = "Energy")
    @Enumerated(EnumType.STRING)
    private EnergyType energy;

    @Column(name = "Transmission")
    @Enumerated(EnumType.STRING)
    private TransmissionType gear;

    @Column(name ="Door")
    private int nbDoors;

    @Column(name = "Places")
    private int nbPlace;

    @Column (name = "Power")
    private int power;

    @OneToMany(mappedBy = "vType",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<Vehicle> vehicleSet;


    /* ------- CONSTRUCTOR ------- */
    // Default
    public VehicleType(){

    }

    // Data
    public VehicleType(String brand, String model, EnergyType energy, TransmissionType gear, int nbDoors,
                       int nbPlace, int power) {
        if (StringUtils.checkString(brand) && StringUtils.checkString(model) && nbDoors >=3 && nbPlace >= 2 && power >0) {
            this.brand = brand;
            this.model = model;
            this.energy = energy;
            this.gear = gear;
            this.nbDoors = nbDoors;
            this.nbPlace = nbPlace;
            this.power = power;
            this.vehicleSet = new HashSet<>();
        }
    }

    /* ------- GETTER & SETTER ------- */
    public int getNbDoors() {
        return nbDoors;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public Set<Vehicle> getVehicleSet() {
        return vehicleSet;
    }

    public void setVehicleSet(Set<Vehicle> vehicleSet) {
        this.vehicleSet = vehicleSet;
    }
    /* ------- METHODS ------- */
    public boolean addVehicle(Vehicle v){
        if(v==null){return false;}
        if(this.vehicleSet.contains(v)){return false;}
        v.setvType(this);
        this.vehicleSet.add(v);
        return true;
    }

    public boolean remove(Vehicle v){
        if(v==null){return false;}
        if(!this.vehicleSet.contains(v)){return false;}
        v.setvType(null);
        this.vehicleSet.remove(v);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VehicleType that = (VehicleType) o;
        return Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }

    @Override
    public String toString() {
        return "VehicleType{" +
                "idVehiculeType=" + idVehiculeType +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", energy=" + energy +
                ", gear=" + gear +
                ", nbDoors=" + nbDoors +
                ", nbPlace=" + nbPlace +
                ", power=" + power +
                ", vehicleSet=" + vehicleSet +
                '}';
    }

    /* ------- MAIN ------- */
    static void main() {
    }
}
