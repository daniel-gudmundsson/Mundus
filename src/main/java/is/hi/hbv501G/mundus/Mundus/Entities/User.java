package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class User {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name;
    private String pin;
}
