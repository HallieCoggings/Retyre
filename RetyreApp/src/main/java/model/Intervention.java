package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import model.enums.*;
import java.time.LocalDate;
import java.util.*;

/**
 * <h1>Intervention</h1>
 * Used for any kinf od intervention
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

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Intervention(){

    }

    public Intervention(Vehicle v, InterventionType interType, LocalDate interventionDate){
        if (v !=null && interType !=null && interventionDate!=null){
            this.vehicle =v;
            this.interventionType = interType;
            this.interventionDate = interventionDate;
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
    }
    /* ------- METHODS ------- */
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

