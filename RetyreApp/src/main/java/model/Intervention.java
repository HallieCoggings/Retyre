package model;

import jakarta.persistence.*;
import model.enums.*;
import java.time.LocalDate;
import java.util.*;

/**
 * <h1>Intervention</h1>
 * Used for any kind of intervention
 * v1.0
 */

@Entity
public class Intervention {

    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_Intervention")
    private Integer idIntervention;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="FK_Vehicle")
    private Vehicle vehicle;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="FK_Employee")
    private Employee employee;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="FK_IntervType")
    private InterventionType interventionType;

    @Column(name="DateIntervention")
    private LocalDate interventionDate;

    @Column(name="Price",columnDefinition = "NUMERIC(10,2)")
    private double price;

    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    private InterventionStatus status;

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Intervention(){

    }

    public Intervention(Vehicle v, InterventionType interType, LocalDate interventionDate){
        if (v !=null && interType !=null && interventionDate!=null){
            this.vehicle =v;
            this.interventionDate = interventionDate;
            this.price = 0;
            this.status = InterventionStatus.ONGOING;
            if(!interType.addIntervention(this)){return;}
        }
    }

    /* ------- GETTER & SETTER ------- */
    public InterventionType getInterventionType() {
        return interventionType;
    }

    public void setInterventionType(InterventionType interventionType) {
        this.interventionType = interventionType;
    }

    public void setEmployee(Employee employee) {
        employee.addIntervention(this);
        this.employee =employee;
    }

    /* ------- METHODS ------- */
    public boolean beginIntervention (){
        if (this.status != InterventionStatus.ONGOING) {return false;}
        this.status=InterventionStatus.ONHOLD;
        return true;
    }

    public boolean endIntervention () {
            if (this.status != InterventionStatus.ONHOLD) {return false;}
            this.status = InterventionStatus.DONE;

            for (Piece p : this.interventionType.getPiecesUsed()){
                this.price+=p.getPriceU();
            }

            this.price += (vehicle.getvType().getNbDoors()+vehicle.getvType().getNbPlace())*5;
            return true;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Intervention that = (Intervention) o;
        return Objects.equals(idIntervention, that.idIntervention) && Objects.equals(vehicle, that.vehicle) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIntervention, vehicle, employee);
    }

    @Override
    public String toString() {
        return "Intervention{" +
                "idIntervention=" + idIntervention +
                ", vehicle=" + vehicle +
                ", employee=" + employee +
                ", interventionType=" + interventionType +
                ", interventionDate=" + interventionDate +
                '}';
    }

    /* ------- MAIN ------- */
    static void main() {

    }
}

