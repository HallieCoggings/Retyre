package model;

public class Employee extends Person{
    /* ------- PARAMETERS ------- */
    private String speciality; //TODO : Create Enum class with Specilities

    /* ------- CONSTRUCTOR ------- */
    public Employee(){

    }

    public Employee(String name, String fName, String spe) {
        super(name,fName);
        if (super.checkString(spe)){
            this.speciality = spe;
        }
    }

    /* ------- GETTER & SETTER ------- */

    /* ------- METHODS ------- */

    /* ------- MAIN ------- */
    static void main() {
        
    }
}
