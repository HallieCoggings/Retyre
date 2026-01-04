package model;

/**
 * <h1>InterventionType</h1>
 * Represents what kind of intervention can be done on a vehicle
 */

//necessary imports
    
    
@Entity
@Table(name = "intervention_type")
public class InterventionType {
    
    //_________________PARAMETERS______________________
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_intervention_type")
    private Integer id;

    @NotBlank(message = "Intervention name is mandatory")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InterventionCategory category;

    // Only for maintenance
    @Min(value = 0, message = "Max mileage must be positive")
    @Column(name = "km_max")
    private Integer kmMax;

    @Min(value = 0)
    @Column(name = "max_month_duration")
    private Integer maxMonthsDuration;

    @ManyToMany
    @JoinTable(
            name = "interventiontype_piece",
            joinColumns = @JoinColumn(name = "id_intervention_type"),
            inverseJoinColumns = @JoinColumn(name = "id_piece")
    )
    private Set<Piece> piecesToUse = new HashSet<>();

    //_________________CONSTRUCTORS______________________
    //default
    public InterventionType() {}

    //Data
    public InterventionType(String name, InterventionCategory category) {
        this.name = name;
        this.category = category;
    }

    //_________________METHODS______________________
    public boolean isMaintenance() {
        return this.category == InterventionCategory.MAINTENANCE;
    }

    //TODO- Getter-Setters
}
