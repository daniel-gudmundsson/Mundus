package is.hi.hbv501G.mundus.Mundus.Entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Quest implements Comparable<Quest> {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @Column(name = "QuestId")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    @NotBlank(message = "The quest must have a name")
    private String name; // Name of the quest
    @NotBlank(message = "The quest must have a description")
    private String description; // Description of the quest
    @NotNull(message = "The quest must reward some xp")
    private int xp; // xp gained for completing the quest
    @NotNull(message = "The quest must reward some coins")
    private int coins; // Coins gained for completing the quest
    private LocalDate dateCreated; // Breyta í data format eða e-h þannig
    @NotNull(message = "The quest must have some deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline; // Due date of the quest
    @ManyToOne
    private Child assignee; // The child that the quest has been assigned to
    private Boolean isDone; // true if the quest is done, false otherwise
    private Boolean isConfirmed; // true if the parent has confirmed that the quest is indeed done, false otherwise.
    @ManyToOne
    private Parent maker; // Creator of the quest

    /**
     * Assignee is null by defult. isDone is false by defult.
     *
     * @param name
     * @param description
     * @param xp
     * @param coins
     * @param deadline
     */
    public Quest(String name, String description, int xp, int coins, LocalDate deadline, Parent maker) {
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.coins = coins;
        this.dateCreated = LocalDate.now();
        this.deadline = deadline;
        this.assignee = null;
        this.isDone = false;
        this.isConfirmed = false;
        this.maker = maker;
    }

    public Quest() {
        this.dateCreated = LocalDate.now();
        this.isDone = false;
        this.isConfirmed = false;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
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

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public int compareTo(@org.jetbrains.annotations.NotNull Quest o) {
        return 0;
    }
}
