package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reward {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name;
    private String description;
    private int price;
    private int levelRequired;
    private boolean autorenew;
    private String endDate;
    private Boolean visible;

}
