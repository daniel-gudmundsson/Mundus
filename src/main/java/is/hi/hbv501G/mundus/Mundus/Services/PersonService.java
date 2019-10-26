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
<<<<<<< HEAD
    void assignChildToParent(Child child, long idOfParent) throws Exception;
=======
    void assignQuestToChild(long idOfQuest, long idOfChild) throws Exception;
    void assignChildToParent(long idOfChild, long idOfParent) throws Exception;

>>>>>>> 938b195be5e9b12d9477af07864a0274a5796840
}
