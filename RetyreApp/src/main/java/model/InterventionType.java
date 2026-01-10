package model;

import jakarta.persistence.*;
import model.enums.*;
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

    @Column(name="Category")
    private InterventionCategory category;

    @Column(name = "kmMax")
    private int kmMax;

    @Column(name="DaysBetween")
    private int delay;

    @OneToMany(mappedBy = "interventionType")
    private Set<Intervention> interventionSet;

    /* ------- CONSTRUCTOR ------- */
    //Default
    public InterventionType() {

    }

    //Data
    public InterventionType(InterventionCategory category, int kmMax, int delay) {
        if (category != null && kmMax >0 && delay>0) {
            this.category = category;
            this.interventionSet = new HashSet<>();
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InterventionType that = (InterventionType) o;
        return idInterventionType == that.idInterventionType && kmMax == that.kmMax && delay == that.delay && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInterventionType, category, kmMax, delay);
    }

    @Override
    public String toString() {
        return "InterventionType{" +
                "idInterventionType=" + idInterventionType +
                ", category=" + category +
                ", kmMax=" + kmMax +
                ", delay=" + delay +
                ", interventionSet=" + interventionSet +
                '}';
    }

    /* ------- MAIN ------- */
    static void main() {

    }

}
