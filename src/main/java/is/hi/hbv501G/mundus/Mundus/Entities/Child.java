package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Child extends Person {

    private int totalCoins;
    private int xp;
    @OneToMany
    private Set<Quest> quests = new HashSet<>();






    @OneToMany(mappedBy = "child",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
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

    public int getLevel(){
        return 99;
    }
}
