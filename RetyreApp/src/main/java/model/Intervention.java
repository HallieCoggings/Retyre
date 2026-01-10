package model;

import com.mysql.cj.protocol.ColumnDefinition;
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
@Table(name = "intervention")
public class Intervention {

    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_Intervention")
    private Integer id;

    /* -- J'ai craque mon slip, ce n'est pas du tout ici qu'il doit etre celui-la mauvaise table mais tu peux t'en servir pour vehicule du coup stv

    @NotBlank(message = "The vehicle must have a matriculation / Le vehicule doit avoir une plaque d'immatriculation")
    @Size(min=7, max=15, message="Matriculation must be valid format / La plaque d'immatriculation doit avoir un format valide \n Must be between 7 and 15 alphabetical and numerical symbols separated by '-' / Doit comprendre entre 7 et 15 caracteres et etre separes par un '-' ")
    @Pattern(
            regexp = "^[A-Z0-9\\s]{7,15}$",
            message = "Invalid format: all caps letters, numbers, and dash only \n" +
                    "Format invalide: lettres majuscules, numeros et tirets seulement"
    )
    @Column(name="matriculation", length = 15, nullable = false, unique = true)
    private String matriculation;
     */

    @NotNull(message = "There must be a vehicle / Le vehicule est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matriculation", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_TypeIntervention", nullable = false)
    @NotNull(message = "You must enter an intervention type / Vous devez entrer un type d'intervention")
    private InterventionType interventionType;

    @NotNull(message = "Beginnind date must not be null / Il doit y avoir une date de debut d'intervention")
    @FutureOrPresent(message = "Beginning date cannot be in the future / La date de debut doit etre anterieure a la date d'aujourd'hui")
    @Column(name = "beginningDate", nullable = false)
    private LocalDate beginningDate;

    @Column(name = "endingDate")
    private LocalDate endingDate;

    @NotNull(message = "Mileage must not be null/ Le kilometrage est obligatoire")
    @Min(value = 0, message = "Mileage must be more than 0 / Le kilometrage doit etre superieure a 0")
    @Column(name = "mileageIntervention", nullable = false)
    private Integer mileageIntervention;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private model.InterventionStatus status;

    @Size(max=5000, message = "Comment cannot exceeds 5000 characters / Le commentaire ne peut pas depasser 5000 caracteres ")
    @Column(name = "comment")
    private String comment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="Intervention_Piece",
            joinColumns = @JoinColumn(name = "id_intervention"),
            inverseJoinColumns = @JoinColumn(name = "id_Piece")
    )
    private Set<Piece> pieces = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="Intervention_Employee",
            joinColumns = @JoinColumn(name = "id_Intervention"),
            inverseJoinColumns = @JoinColumn(name = "id_Employee")
    )
    private Set<Employee> employees = new HashSet<>();

    /* ------- CONSTRUCTORS ------- */

    public Intervention() {}

    public Intervention(Vehicle vehicle, InterventionType interventionType, LocalDate beginningDate, Integer mileageIntervention){
        this();
        this.vehicle = vehicle;
        this.interventionType = interventionType;
        this.beginningDate = beginningDate;
        this.mileageIntervention = mileageIntervention;
    }

    /* ------- METHODS ------- */

    //checks if intervention = repair
    public boolean isRepair() {
        return interventionType != null && interventionType.isRepair();
    }

    //checks if intervention = maintenance
    public boolean isMaintenance() {
        return interventionType != null && interventionType.isMaintenance();
    }

    //TODO : method for urgency level

    //TODO: method to get urgency level

    //TODO: method to calculate the cost of intervention

    //Complete intervention
    public void complete() {
        if (status == model.InterventionStatus.DONE) {
            throw new IllegalStateException("Intervention is already completed");
        }
        if (status == model.InterventionStatus.CANCELED) {
            throw new IllegalStateException("Cannot complete a cancelled intervention");
        }

        this.status = model.InterventionStatus.DONE;
        this.endingDate = LocalDate.now();
    }

    //Cancels intervention
    public void cancel() {
        if (status == model.InterventionStatus.DONE) {
            throw new IllegalStateException("Cannot cancel a completed intervention");
        }

        this.status = model.InterventionStatus.CANCELED;
        this.endingDate = LocalDate.now();
    }

    //TODO: Add isModifiable() to InterventionStatus

    //Add piece to intervention
    public void addPiece(Piece piece) {
        if (piece != null && status.isModifiable()) {
            this.pieces.add(piece);
        }
    }

    //Adds employee to intervention
    public void addEmployee(Employee employee) {
        if (employee != null && status.isModifiable()) {
            this.employees.add(employee);
            employee.getInterventions().add(this);
        }
    }

    public boolean isCompleted() {
        return this.status == model.InterventionStatus.DONE;
    }


    /* ------- GETTERS AND SETTERS ------- */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public InterventionType getInterventionType() {
        return interventionType;
    }

    public void setInterventionType(InterventionType interventionType) {
        this.interventionType = interventionType;
    }

    public LocalDate getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(LocalDate beginningDate) {
        this.beginningDate = beginningDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public Integer getMileageIntervention() {
        return mileageIntervention;
    }

    public void setMileageIntervention(Integer mileageIntervention) {
        this.mileageIntervention = mileageIntervention;
    }

    public model.InterventionStatus getStatus() {
        return status;
    }

    public void setStatus(model.InterventionStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comment;
    }

    public void setComments(String comments) {
        this.comment = comments;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    /* ------- EQUALS, HASHCODE AND TOSTRING ------- */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Intervention that = (Intervention) o;
        return Objects.equals(id, that.id) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vehicle);
    }

    @Override
    public String toString() {
        return "Intervention{" +
                "vehicle=" + vehicle +
                ", interventionType=" + interventionType +
                ", beginningDate=" + beginningDate +
                ", endingDate=" + endingDate +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", employees=" + employees +
                '}';
    }



}
