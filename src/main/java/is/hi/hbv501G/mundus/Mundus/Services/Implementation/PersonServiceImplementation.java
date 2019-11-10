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


    /**
     * A method for assigning a child to a parent. When a parent creates a new child account the child should
     * be assigned to the parent
     *
     * @param child
     * @param idOfParent
     * @throws Exception
     */
    @Override
    public void assignChildToParent(Child child, long idOfParent) throws Exception {
        Parent parent = personRepository.findParentById(idOfParent); // Find the parent by id

        if (child == null || parent == null) { // If either of them are null then something is wrong
            throw new Exception();
        } else {
            parent.addChild(child); // Add the child to the parent
            child.setParent(parent); // Add the parent to the child (Only one parent per child)
            personRepository.save(parent);
        }
    }

    /**
     * A method for authenticating the pin when trying to log into a person.
     *
     * @param childId
     * @param pin
     * @return
     * @throws Exception
     */
    @Override
    public long authenticatePin(long childId, String pin) throws Exception {
        Person person = personRepository.findPersonById(childId); // Get the person trying to log in

        if (person == null) {
            throw new LoginException("Person not found");
        }

        if (person.getPin().equals(pin)) { // The pin is correct
            return person.getId();
        } else {
            throw new FailedLoginException("Wrong pin"); // The pin was incorrect
        }


    }

}
