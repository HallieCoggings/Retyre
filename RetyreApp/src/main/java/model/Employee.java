package model;

import utils.StringUtils;

/**
 * <h1>Employee</h1>
 * represents a garage's employee
 */
public class Employee extends Person{
    /* ------- PARAMETERS ------- */
    private String speciality; //TODO : Create Enum class with Specilities

    /* ------- CONSTRUCTOR ------- */
    public Employee(){

    }

    public Employee(String name, String fName, String spe) {
        super(name,fName);
        if(StringUtils.checkString(spe)){
            this.speciality=spe;
        }
    }

    /* ------- GETTER & SETTER ------- */

    /* ------- METHODS ------- */

    /* ------- MAIN ------- */
    static void main() {

    }
}
