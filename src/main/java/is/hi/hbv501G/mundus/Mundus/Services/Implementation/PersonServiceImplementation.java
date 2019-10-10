package is.hi.hbv501G.mundus.Mundus.Services.Implementation;


import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonServiceImplementation implements PersonService {

    PersonRepository repository;

    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository){this.repository = personRepository;}


    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public void delete(Person person) {
        repository.delete(person);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> findById(long id) {
        return repository.findById(id);
    }
}
