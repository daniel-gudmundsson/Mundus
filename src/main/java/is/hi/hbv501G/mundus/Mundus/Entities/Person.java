package is.hi.hbv501G.mundus.Mundus.Entities;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public abstract class Person implements Comparable<Person> { // An abstract class which Child and Parent are based on

    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @Column(name = "PersonId")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;
    @NotBlank
    private String name; // Name of the person
    //@Pattern(regexp="^[0-9]*$")
    @NotBlank
    private String pin; // Pin to log in to the person's account

    public Person(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

    public Person() {

    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        return this.name.compareTo(o.getName());
    }
}
