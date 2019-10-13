package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

public class Parent {

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Child> childs = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Reward> rewards = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Quest> quests = new HashSet<>();


}
