package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * <h1>Person</h1>
 * Abstract class representing a person.
 * Parent class for Owner and Employee
 */
/*
public abstract class Person {
    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPerson;

    @Column(name = "Name",nullable = false,length = 255)
    private String name;

    @Column(name = "First Name", nullable = false, length = 255)
    private String firstName;

    /* ------- CONSTRUCTOR ------- */
    //Default
    public Person(){
    }

    public Person(String name, String firstName) {
        if (StringUtils.checkString(name) && StringUtils.checkString(firstName)) {
            this.name = name;
            this.firstName = firstName;
        }
    }

    /* ------- GETTER & SETTER ------- */



    /* ------- METHODS ------- */
}

*/
@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "type_person",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
public abstract class Person {

    /* ------- PARAMETERS ------- */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer id;

    @NotBlank(message = "Name is mandatory / Le nom est obligatoire")
    @Size(max = 255, message = "Name cannot exceed 255 characters / Le nom ne peut pas dépasser 255 caractères")
    @Column(nullable = false, length = 255)
    private String name;

    @NotBlank(message = "First name is mandatory / Le prénom est obligatoire")
    @Size(max = 255, message = "First name cannot exceed 255 characters / Le prénom ne peut pas dépasser 255 caractères")
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Past(message = "Date of birth must be in the past / La date de naissance doit être dans le passé")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Pattern(
            regexp = "^0[1-9]\\d{8}$",
            message = "Invalid phone format (expected: 10 digits starting with 0) / Format invalide (attendu: 10 chiffres commençant par 0)"
    )
    @Column(length = 10)
    private String telephone;

    /* ------- CONSTRUCTORS ------- */

    protected Person() {}

    public Person(String name, String firstName) {
        if (checkString(name) && checkString(firstName)) {
            this.name = name.trim();
            this.firstName = firstName.trim();
        } else {
            throw new IllegalArgumentException("Name and first name cannot be null or empty");
        }
    }

    public Person(String name, String firstName, LocalDate dateOfBirth, String telephone) {
        this(name, firstName);
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
    }


    /* ------- METHODS ------- */

    protected boolean checkString(String s) {
        return s != null && !s.trim().isEmpty();
    }

    //to have homogeneous results
    protected String normalizeString(String s) {
        if (!checkString(s)) {
            return s;
        }
        s = s.trim();
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
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
        if (checkString(name)) {
            this.name = name.trim();
        } else {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (checkString(firstName)) {
            this.firstName = firstName.trim();
        } else {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFullName() {
        return firstName + " " + name;
    }

    //In case someone wants to put the name and first name in reverse order
    public String getFullNameReversed() {
        return name + " " + firstName;
    }
    //TODO ? : get initials


    public Integer getAge() {
        if (dateOfBirth == null) {
            return null;
        }
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public Boolean isAdult() {
        Integer age = getAge();
        return age != null ? age >= 18 : null;
    }

    public boolean hasPhoneNumber() {
        return checkString(telephone);
    }

    public String getFormattedPhone() {
        if (telephone == null || telephone.length() != 10) {
            return telephone;
        }
        return telephone.substring(0, 2) + " " +
                telephone.substring(2, 4) + " " +
                telephone.substring(4, 6) + " " +
                telephone.substring(6, 8) + " " +
                telephone.substring(8, 10);
    }



    /* ------- EQUALS, HASHCODE AND TOSTRING ------- */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null && person.id != null) {
            return Objects.equals(id, person.id);
        }

        return Objects.equals(name, person.name) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
