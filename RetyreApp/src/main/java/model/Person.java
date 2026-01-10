package model;

import jakarta.persistence.*;
import utils.StringUtils;

/**
 * <h1>Person</h1>
 * Abstract - Represent a person
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="P_Type",discriminatorType = DiscriminatorType.STRING)
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
