package is.hi.hbv501G.mundus.Mundus.Entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quest {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name;
    private String description;
    private int xp;
    private int coins;
    private String dateCreated; // Breyta í data format eða e-h þannig
    private String deadline;
    private User assignee;
    private Boolean isDone;
}
