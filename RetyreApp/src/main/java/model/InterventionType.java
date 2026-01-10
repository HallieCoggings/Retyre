package model;

import jakarta.persistence.*;
import model.enums.*;
import utils.StringUtils;
import java.util.*;

/**
 * <h1>InterventionType</h1>
 * Represents what kind of intervention can be done on a vehicle.
 * v1.0
 */
@Entity
public class InterventionType {

    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInterventionType;

    @Column(name="Name")
    private String name;

    @Column(name="Category")
    @Enumerated(EnumType.STRING)
    private InterventionCategory category;

    @Column(name = "kmMax")
    private int kmMax;

    @Column(name="DaysBetween")
    private int delay;

    @OneToMany(mappedBy = "interventionType")
    private Set<Intervention> interventionSet;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PiecesUsed",
            joinColumns = @JoinColumn(name = "FK_InterventionType"),
            inverseJoinColumns = @JoinColumn(name = "FK_Piece")
    )
    private Set<Piece> piecesUsed = new HashSet<>();
    /* ------- CONSTRUCTOR ------- */
    //Default
    public InterventionType() {

    }

    //Data
    public InterventionType(String name,InterventionCategory category, int kmMax, int delay) {
        if (StringUtils.checkString(name) && category != null && kmMax >=0 && delay>=0) {
            this.name = name;
            this.category = category;
            this.interventionSet = new HashSet<>();
            this.piecesUsed = new HashSet<>();
            if (category == InterventionCategory.MAINTENANCE) {
                this.kmMax = kmMax;
                this.delay = delay;
            }
        }
    }

    /* ------- GETTER & SETTER ------- */

    /* ------- METHODS ------- */
    public boolean addIntervention (Intervention i){
        if (i==null){return false;}
        if (this.interventionSet.contains(i)){return false;}

        i.setInterventionType(this);
        this.interventionSet.add(i);

        return true;
    }

    public boolean addPieceUsed (Piece p){
        if (p==null){return false;}
        if (this.piecesUsed.contains(p)){return false;}

        if(!p.addUsage(this)) {return false;}
        this.piecesUsed.add(p);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InterventionType that = (InterventionType) o;
        return kmMax == that.kmMax && delay == that.delay && Objects.equals(name, that.name) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, kmMax, delay);
    }

    @Override
    public String toString() {
        return "InterventionType{" +
                "idInterventionType=" + idInterventionType +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", kmMax=" + kmMax +
                ", delay=" + delay +
                ", interventionSet=" + interventionSet +
                ", piecesUsed=" + piecesUsed +
                '}';
    }

    /* ------- MAIN ------- */
    static void main() {

    }

}
