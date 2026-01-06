package model;

/**
 *<h1>Piece</h1>
 * Represent a part of a Vehicle
 */
public class Piece {
    /* ------- PARAMETERS ------- */
    private String reference;
    private String name;
    private float price;
    private String description;
    private String category;

    /* ------- CONSTRUCTOR ------- */
    public Piece(){}

    public Piece (String ref, String name,float price, String desc, String ctg){
        if (this.checkString(ref) && this.checkString(name) && price>0 &&
                this.checkString(desc) && this.checkString(ctg)){
            this.reference = ref;
            this.name = name;
            this.price = price;
            this.description = desc;
            this.category = ctg;
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
    private boolean checkString (String s){
        return (s!=null && !s.isEmpty());
    }

    /* ------- MAIN ------- */
    static void main() {

    }
}
