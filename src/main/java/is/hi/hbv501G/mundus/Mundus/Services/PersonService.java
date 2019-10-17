package is.hi.hbv501G.mundus.Mundus.Services;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import is.hi.hbv501G.mundus.Mundus.Entities.Quest;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);
    void delete(Person person);
    List<Person> findAll();
    Child findChildById(long id);
    Parent findParentById(long id);
    void assignQuestToChild(long idOfQuest, long idOfChild) throws Exception;
    void assignChildToParent(long idOfChild, long idOfParent) throws Exception;
}
