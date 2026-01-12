package model;

import jakarta.persistence.*;
import utils.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *<h1>Piece</h1>
 * Represent a part of a Vehicle
 */
@Entity
public class Piece {
    /* ------- PARAMETERS ------- */
    @Id
    @Column(name="Ref")
    private String reference;

    @Column(name="Name")
    private String name;

    @Column(name="PU_eur)",columnDefinition = "NUMERIC(10,2)")
    private double priceU;

    @Column(name="Description")
    private String description;

    @Column(name="Category")
    private String category;

    @ManyToMany(mappedBy = "piecesUsed")
    private Set<InterventionType> usedIn;

    @ManyToMany(mappedBy = "components")
    private Set<VehicleType> installedOn;


    /* ------- CONSTRUCTOR ------- */
    public Piece(){}

    public Piece(String ref, String name, double priceU, String description, String category) {
        if (StringUtils.checkString(ref)&&StringUtils.checkString(name) && StringUtils.checkString(description) && StringUtils.checkString(category)
            && priceU >0) {
            this.reference =ref;
            this.name = name;
            this.priceU = priceU;
            this.description = description;
            this.category = category;
            this.usedIn = new HashSet<>();
            this.installedOn = new HashSet<>();
        }
    }

    /* ------- GETTER & SETTER ------- */
    public double getPriceU() {
        return priceU;
    }

    public String getName() {
        return name;
    }

    /* ------- METHODS ------- */
    public boolean addUsage (InterventionType i){
        if (i==null){return false;}
        if(this.usedIn.contains(i)){return false;}
        this.usedIn.add(i);
        return true;
    }

    public boolean addInstallation (VehicleType v){
        if (v==null){return false;}
        if (this.installedOn.contains(v)) {return false;}
        this.installedOn.add(v);
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(reference, piece.reference) && Objects.equals(name, piece.name) && Objects.equals(category, piece.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, name, category);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "reference='" + reference + '\'' +
                ", name='" + name + '\'' +
                ", priceU=" + priceU +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
    /* ------- MAIN ------- */
    static void main() {

    }
}
