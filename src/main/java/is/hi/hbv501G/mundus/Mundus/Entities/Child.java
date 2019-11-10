package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Child extends Person { // This class extends the abstract Person class

    private int totalCoins; // The total amount of coins the child has. Can be used to buy rewards
    private int xp; // The total amount of xp the child has (Need xp to level up) TODO MAKE A LEVEL UP FUNCTION
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "assignee",orphanRemoval = true)
    private Set<Quest> quests = new HashSet<>(); // Contains quests that are assigned to the child
    @ElementCollection
    private List<Long> rewards = new ArrayList<>(); // Contains the id of the rewards the child owns

    @NotNull
    @ManyToOne
    private Parent parent; // The parent of the child

    public Child(String name, String pin) {
        super(name, pin);
        this.totalCoins = 0;
        this.xp = 0;
    }

    public Child(){

    }

    // Getters and Setters
    @OneToMany
    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    public List<Long> getReward() {
        return rewards;
    }

    public void setRewards(List<Long> rewardIds) {
        this.rewards = rewardIds;
    }

    public void addReward(long rewardId){
        this.rewards.add(rewardId);
    }

    public void removeReward(long rewardId){
        this.rewards.remove(rewardId);
    }

    public int getTotalCoins() {

        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    public int getLevel() {

        return xp/1000;
    }

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public void removeQuest(Quest quest) {
        quests.remove(quest);
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


    public void addXp(int xp){
        this.xp=this.xp+xp;
    }

    public void addCoins(int coins){this.totalCoins = this.totalCoins + coins;}

}
