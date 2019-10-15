package is.hi.hbv501G.mundus.Mundus.Repositories;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Parent save(Parent parent);
    Child save(Child child);

    void delete(Person person);
    List<Person> findAll();
    Child findChildById(long id);
    Parent findParentById(long id);

}
