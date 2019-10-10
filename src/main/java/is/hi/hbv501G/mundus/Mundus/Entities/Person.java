package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;

@Entity
public abstract class Person {

    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @Column(name = "PersonId")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name;
    private String pin;


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
}
