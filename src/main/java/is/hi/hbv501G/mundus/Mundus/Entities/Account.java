package is.hi.hbv501G.mundus.Mundus.Entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
public class Account {
    @Id //Segir að id eigi að vera aðalykillinn í töflunni okkar
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Java býr til nýtt gildi sjálfkrafa þegar nú mynd er búinn til
    @Column(name = "AccountId")
    private long id;

    private String name;
    private String email;
    private String password;
    private SimpleDateFormat dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "account")
    private Parent parent;

    public Account(String name, String email, String password, SimpleDateFormat dateOfBirth, Parent parent) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.parent = parent;
    }

    public Account(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SimpleDateFormat getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(SimpleDateFormat dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }


}
