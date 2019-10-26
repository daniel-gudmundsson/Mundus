package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Parent extends Person {

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "parent",orphanRemoval = true)
    private Set<Child> children = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "maker",orphanRemoval = true)
    private Set<Reward> rewards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "maker",orphanRemoval = true)
    private Set<Quest> quests = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountId")
    private Account account;


    public Parent(String name, String pin) {
        super(name, pin);
    }

    public Parent(){

    }

    public void addChild(Child child){
        children.add(child);
    }



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
}
