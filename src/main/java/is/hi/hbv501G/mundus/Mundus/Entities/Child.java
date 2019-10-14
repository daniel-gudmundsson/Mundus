package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Child extends Person {

    private int totalCoins;
    private int xp;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Quest> quests = new HashSet<>();

    @ManyToOne
    private Parent parent;

    public Child(String name, String pin, int totalCoins, int xp, Parent parent) {
        super(name, pin);
        this.totalCoins = totalCoins;
        this.xp = xp;
        this.parent = parent;
    }

    public Child(){

    }

    @OneToMany(mappedBy = "child",
            orphanRemoval = true)
    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    public int getTotalCoins() {

        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    public int getLevel() {
        return 99;
    }

    public void addQuest(Quest quest) {
        quest.setAssignee(this);
        quests.add(quest);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "PersonId")
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
