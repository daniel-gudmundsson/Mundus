package is.hi.hbv501G.mundus.Mundus.Services.Implementation;


import is.hi.hbv501G.mundus.Mundus.Entities.*;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.QuestRepository;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.util.List;

@Service
public class PersonServiceImplementation implements PersonService {

    PersonRepository personRepository;
    QuestRepository questRepository;

    @Autowired
    public PersonServiceImplementation(PersonRepository personRepository, QuestRepository questRepository) {
        this.personRepository = personRepository;
        this.questRepository = questRepository;
    }


    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Person person) {
        personRepository.delete(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Child findChildById(long id) {
        return personRepository.findChildById(id);
    }

    @Override
    public Parent findParentById(long id) {
        return personRepository.findParentById(id);
    }

    @Override
    public Person findPersonById(long id) {
        return personRepository.findPersonById(id);
    }


    @Override
    public void assignChildToParent(Child child, long idOfParent) throws Exception {
        Parent parent = personRepository.findParentById(idOfParent);

        if (child == null || parent == null) {
            throw new Exception();
        } else {
            parent.addChild(child);
            child.setParent(parent);
            personRepository.save(child);
            personRepository.save(parent);
        }
    }

    @Override
    public long authenticatePin(long childId, String pin) throws Exception {
        Person person = personRepository.findPersonById(childId);

        if (person == null) {
            throw new LoginException("Person not found");
        }

        if (person.getPin().equals(pin)) {
            return person.getId();
        } else {
            throw new FailedLoginException("Wrong pin");
        }


    }

}
