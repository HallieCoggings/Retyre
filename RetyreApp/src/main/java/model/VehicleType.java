package model;

/**
 * <h1>VehicleType</h1>
 * Represent a type of vehicle used in the application
 */
public class VehicleType {
    /* ------- PARAMETERS ------- */
    private String brand;
    private String model;
    private String energy;
    private String gear;
    private int nbDoors;
    private int nbPlace;
    private int power;

    /* ------- CONSTRUCTOR ------- */
    // Default
    public VehicleType(){

    }

    // Data
    public VehicleType(String brand, String model, String energy, String gear, int nbDoors, int nbPlace, int power) {
        if (this.checkString(brand) && this.checkString(model) && this.checkString(energy)
                && this.checkString(gear) && nbDoors>3 && nbPlace>2 && power >0) {
            this.brand = brand;
            this.model = model;
            this.energy = energy;
            this.gear = gear;
            this.nbDoors = nbDoors;
            this.nbPlace = nbPlace;
            this.power = power;
        }else{
            return; // Maybe better to throw an exception
        }
    }

    /* ------- GETTER & SETTER ------- */



    /* ------- METHODS ------- */
    // 1) checkString
    /**
     * <h1>checkString</h1>
     * Check if a string is null or empty
     * @param s string to test
     * @return boolean
     */
    // TO DO : Move to class/pacakge "Utilities" if there is a multiple usages
    private boolean checkString (String s){
        return (s!=null && !s.isEmpty());
    }

    /* ------- PSVM ------- */
    static void main() {
    }
}
