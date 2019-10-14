package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;

@Entity
public class Reward {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @Column(name = "RewardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    private long id;

    private String name;
    private String description;
    private int price;
    private int levelRequired;
    private boolean autorenew;
    private String endDate;
    private Boolean visible;
    @ManyToOne
    private Parent maker;

    public Reward(String name, String description, int price, int levelRequired, boolean autorenew, String endDate, Boolean visible, Parent maker) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.levelRequired = levelRequired;
        this.autorenew = autorenew;
        this.endDate = endDate;
        this.visible = visible;
        this.maker = maker;
    }

    public Reward(){

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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
}
