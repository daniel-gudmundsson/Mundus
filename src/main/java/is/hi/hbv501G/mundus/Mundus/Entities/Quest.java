package is.hi.hbv501G.mundus.Mundus.Entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

@Entity
public class Quest {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @Column(name = "QuestId")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name;
    private String description;
    private int xp;
    private int coins;
    private String dateCreated; // Breyta í data format eða e-h þannig
    private String deadline;
    @ManyToOne
    private Child assignee;
    private Boolean isDone;
    @ManyToOne
    private Parent maker;

    /**
     * Assignee is null by defult. isDone is false by defult.
     *
     * @param name
     * @param description
     * @param xp
     * @param coins
     * @param dateCreated
     * @param deadline
     */
    public Quest(String name, String description, int xp, int coins, String dateCreated, String deadline, Parent maker) {
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.coins = coins;
        this.dateCreated = dateCreated;
        this.deadline = deadline;
        this.assignee = null;
        this.isDone = false;
        this.maker = maker;
    }

    public Quest() {

    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "PersonId")
    public Parent getMaker() {
        return maker;
    }

    public void setMaker(Parent maker) {
        this.maker = maker;
    }

    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "PersonId")
    public Child getAssignee() {
        return assignee;
    }

    public void setAssignee(Child assignee) {
        this.assignee = assignee;
    }


    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
