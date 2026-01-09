package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import model.enums.*;
import java.util.*;

/**
 * <h1>InterventionType</h1>
 * Represents what kind of intervention can be done on a vehicle.
 * v1.0
 */
@Entity
@Table(name = "intervention_type")
public class InterventionType {

    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_TypeIntervention")
    private Integer id;

    @NotBlank(message = "Intervention name is mandatory")
    @Size(max = 100, message = "Name cannot be more than 100 characters / Le nom ne peut pas depasser 100 caracteres")
    @Column(name="name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Category is mandatory / La categorie est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(name="intervention", nullable = false, length = 20)
    private InterventionCategory category;

    @Min(value = 0, message = "Max mileage must be positive / Le kilometrage max doit etre positif")
    @Column(name = "km_max")
    private Integer kmMax;
    
    @Min(value = 0, message = "Max duration must be positive")
    @Column(name = "max_month_duration")
    private Integer maxMonthsDuration;

    //Several intervnetion can use the same pieces and serveral can be used
    //during an intervention
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "intervention_type_piece",
            joinColumns = @JoinColumn(name = "id_intervention_type"),
            inverseJoinColumns = @JoinColumn(name = "id_piece")
    )
    private Set<Piece> piecesToUse = new HashSet<>();

    /* ------- CONSTRUCTORS ------- */

    public InterventionType() {}

    //category can be repair or maintenance
    public InterventionType(String name, InterventionCategory category) {
        this.name = name;
        this.category = category;
    }

    //full constructor for maintenance interventions
    public InterventionType(String name, InterventionCategory category,
                            Integer kmMax, Integer maxMonthsDuration) {
        this.name = name;
        this.category = category;
        this.kmMax = kmMax;
        this.maxMonthsDuration = maxMonthsDuration;
    }

    /* ------- BUSINESS METHODS ------- */

    //returns true if intervention is maintenance
    public boolean isMaintenance() {
        return this.category == InterventionCategory.MAINTENANCE;
    }

    //same with repair
    public boolean isRepair() {
        return this.category == InterventionCategory.REPAIR;
    }

    //checks if there is a mileage limit (implies the car has been in the garage before)
    public boolean hasKilometerLimit() {
        return kmMax != null && kmMax > 0;
    }

    //checks if there is a time limit
    public boolean hasTimeThreshold() {
        return maxMonthsDuration != null && maxMonthsDuration > 0;
    }

    //adds a piece for the intervention
    public void addPieceToUse(Piece piece) {
        if (piece != null) {
            this.piecesToUse.add(piece);
        }
    }

    //removes a piece for the intervention
    public void removeStandardPiece(Piece piece) {
        this.piecesToUse.remove(piece);
    }

    /* ------- GETTERS AND SETTERS ------- */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InterventionCategory getCategory() {
        return category;
    }

    public void setCategory(InterventionCategory category) {
        this.category = category;
    }

    public Integer getKmMax() {
        return kmMax;
    }

    public void setKmMax(Integer kmMax) {
        this.kmMax = kmMax;
    }

    public Integer getMaxMonthsDuration() {
        return maxMonthsDuration;
    }

    public void setMaxMonthsDuration(Integer maxMonthsDuration) {
        this.maxMonthsDuration = maxMonthsDuration;
    }

    public Set<Piece> getPiecesToUse() {
        return piecesToUse;
    }

    public void setPiecesToUse(Set<Piece> piecesToUse) {
        this.piecesToUse = piecesToUse;
    }

    /* ------- EQUALS, HASHCODE AND TOSTRING ------- */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InterventionType that = (InterventionType) o;
        return Objects.equals(id, that.id) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }

    @Override
    public String toString() {
        return "InterventionType{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", kmMax=" + kmMax +
                ", maxMonthsDuration=" + maxMonthsDuration +
                '}';
    }

    /*@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" (").append(category).append(")");

        if (isMaintenance()) {
            if (kmMax != null) {
                sb.append(" - Max: ").append(kmMax).append(" km");
            }
            if (maxMonthsDuration != null) {
                sb.append(" - ").append(maxMonthsDuration).append(" months");
            }
        }

        return sb.toString();
    }*/
}
