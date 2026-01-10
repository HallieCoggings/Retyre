package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>Employee</h1>
 * Represents a garage employee/technician/...
 * Inherits from Person
 */
@Entity
@Table(name = "employee")
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Person {

    /* ------- PARAMETERS ------- */


     //TODO: create an Enum for common specialties ?
    @Size(max = 100, message = "Specialty cannot exceed 100 characters / La specialite ne peut pas depasser 100 caracteres")
    @Column(length = 100)
    private String specialty;


    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    private Set<Intervention> interventions = new HashSet<>();

    /* ------- CONSTRUCTORS ------- */

    public Employee() {
        super();
    }

    public Employee(String name, String firstName) {
        super(name, firstName);
    }

    public Employee(String name, String firstName, String specialty) {
        super(name, firstName);
        if (checkString(specialty)) {
            this.specialty = specialty.trim();
        }
    }

    public Employee(String name, String firstName, LocalDate dateOfBirth,
                    String telephone, String specialty) {
        super(name, firstName, dateOfBirth, telephone);
        if (checkString(specialty)) {
            this.specialty = specialty.trim();
        }
    }

    /* ------- METHODS ------- */

    public void addIntervention(Intervention intervention) {
        if (intervention != null) {
            this.interventions.add(intervention);
            intervention.getEmployees().add(this);
        }
    }

    public void removeIntervention(Intervention intervention) {
        if (intervention != null) {
            this.interventions.remove(intervention);
            intervention.getEmployees().remove(this);
        }
    }

    public int getInterventionCount() {
        return interventions.size();
    }

    public boolean hasSpecialty() {
        return checkString(specialty);
    }

    public Set<Intervention> getOngoingInterventions() {
        return interventions.stream()
                //TODO : finish method isModifiable in status
                .filter(i -> i.getStatus() != null && i.getStatus().isModifiable())
                .collect(Collectors.toSet());
    }

    public Set<Intervention> getCompletedInterventions() {
        return interventions.stream()
                //TODO: finish method isCompleted in Intervention
                .filter(Intervention::isCompleted)
                .collect(Collectors.toSet());
    }

    public boolean isAvailable() {
        return getOngoingInterventions().isEmpty();
    }

    public int getWorkload() {
        return getOngoingInterventions().size();
    }

    public boolean hasMatchingSpecialty(String requiredSpecialty) {
        if (!hasSpecialty() || !checkString(requiredSpecialty)) {
            return false;
        }
        return specialty.toLowerCase().contains(requiredSpecialty.toLowerCase());
    }

    public String getDisplayName() {
        return getFullName() + (hasSpecialty() ? " (" + specialty + ")" : "");
    }

    /* ------- GETTERS AND SETTERS ------- */

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        if (checkString(specialty)) {
            this.specialty = specialty.trim();
        } else {
            this.specialty = null;
        }
    }

    public Set<Intervention> getInterventions() {
        return interventions;
    }

    public void setInterventions(Set<Intervention> interventions) {
        this.interventions = interventions != null ? interventions : new HashSet<>();
    }

    /* ------- TOSTRING ------- */

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", name='" + getFullName() + "'" +
                (hasSpecialty() ? ", specialty='" + specialty + "'" : "") +
                ", interventions=" + getInterventionCount() +
                ", available=" + isAvailable() +
                "}";
    }
}
