package model;

/**
 * <h1>Person</h1>
 * Abstract - Represent a person
 */
public abstract class Person {
    /* ------- PARAMETERS ------- */
    private String name;
    private String firstName;

    /* ------- CONSTRUCTOR ------- */
    public Person(){

    }

    public Person(String name, String fName){
        if (this.checkString(name) && this.checkString(fName)){
            this.name = name;
            this.firstName = fName;
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
    // TODO : Move to class/pacakge "Utilities" if there is a multiple usages
    public boolean checkString (String s){
        return (s!=null && !s.isEmpty());
    }
}
