package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import model.enums.*;
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

    @NotBlank(message = "The vehicle must have a matriculation / Le vehicule doit avoir une plaque d'immatriculation")
    @Size(min=7, max=15, message="Matriculation must be valid format / La plaque d'immatriculation doit avoir un format valide \n Must be between 7 and 15 alphabetical and numerical symbols separated by '-' / Doit comprendre entre 7 et 15 caracteres et etre separes par un '-' ")
    @Pattern(
            regexp = "^[A-Z0-9\\s]{7,15}$",
            message = "Invalid format: all caps letters, numbers, and dash only \n" +
                    "Format invalide: lettres majuscules, numeros et tirets seulement"
    )
    @Column(name="matriculation", length = 15, nullable = false, unique = true)
    private String matriculation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_TypeIntervention", nullable = false)
    @NotNull(message = "You must enter an intervention type / Vous devez entrer un type d'intervention")
    private InterventionType interventionType;

}

