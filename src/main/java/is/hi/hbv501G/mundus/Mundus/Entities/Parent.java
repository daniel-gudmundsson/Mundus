package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Parent extends Person {

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Child> children = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "maker")
    private Set<Reward> rewards = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,mappedBy = "maker")
    private Set<Quest> quests = new HashSet<>();


    public Parent(String name, String pin) {
        super(name, pin);
    }

    public Parent(){

    }

    public void addChild(Child child){
        children.add(child);
    }



    @OneToMany(orphanRemoval = true)
    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    @OneToMany(orphanRemoval = true)
    public Set<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
    }

    @OneToMany(orphanRemoval = true)
    public Set<Quest> getQuests() {
        return quests;
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }
}
