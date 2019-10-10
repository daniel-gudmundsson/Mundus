package is.hi.hbv501G.mundus.Mundus.Services;

import is.hi.hbv501G.mundus.Mundus.Entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);
    void delete(Person person);
    List<Person> findAll();
    Optional<Person> findById(long id);
}
