package is.hi.hbv501G.mundus.Mundus.Entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Reward implements Comparable<Reward> {

    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @Column(name = "RewardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name; // Name of the reward
    private String description; // Description of the reward
    private int price; // The price of the reward
    private int levelRequired; // Minimum level required to buy the reward
    private boolean autorenew; // Probably not going to use this
/*    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate; // Reward expires after this date*/
    private Boolean visible; // Probably not going to use this
    @ManyToOne
    private Child buyer; // Don't think we are going to use this anymore

    @ManyToOne
    private Parent maker; // Creator of the reward

    public Reward(String name, String description, int price, int levelRequired, Parent maker) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.levelRequired = levelRequired;
        /*this.endDate = endDate;*/
        //this.visible = visible;
        this.maker = maker;
    }

    public Reward() {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    public boolean isAutorenew() {
        return autorenew;
    }

    public void setAutorenew(boolean autorenew) {
        this.autorenew = autorenew;
    }

/*    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }*/

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
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
    public Child getBuyer() {
        return buyer;
    }

    public void setBuyer(Child buyer) {
        this.buyer = buyer;
    }


    @Override
    public int compareTo(@org.jetbrains.annotations.NotNull Reward o) {
        return this.price - o.getPrice();
    }
}
