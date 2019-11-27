package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Parent extends Person { // This class extends the abstarct Person class

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval = true)
    private Set<Child> children = new HashSet<>(); // Contains the children of this parent

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "maker", orphanRemoval = true)
    private Set<Reward> rewards = new HashSet<>(); // Contains the reward the parent has created

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "maker", orphanRemoval = true)
    private Set<Quest> quests = new HashSet<>(); // Contains the quests the parent has created

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AccountId")
    private Account account; // The account which the parent belongs to


    public Parent(String name, String pin) {
        super(name, pin);
    }

    public Parent() {

    }

    public void addChild(Child child) {
        children.add(child);
    }


    // Getters and Setters
    @OneToMany
    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    @OneToMany
    public Set<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
    }

    @OneToMany
    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addQuest(Quest quest) {
        this.quests.add(quest);
    }

    public void addReward(Reward reward) {
        this.rewards.add(reward);
    }

    public void removeReward(Reward reward) {
        this.rewards.remove(reward);
    }


}
