package model;

public class Owner extends Person{
    /* ------- PARAMETERS ------- */
    private String personalDetails; //TODO:Add a class "Details"

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Owner(){
        super();
    }

    //Data
    public Owner(String name, String fName, String pDetails) {
        super(name,fName);
        if (super.checkString(pDetails)){
            this.personalDetails = pDetails;
        }
    }

    /* ------- GETTER & SETTER ------- */

    /* ------- METHODS ------- */

    /* ------- MAIN ------- */
    static void main() {

    }
}
