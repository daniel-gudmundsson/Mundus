package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.QuestRepository;
import is.hi.hbv501G.mundus.Mundus.Services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestServiceImplementation implements QuestService {

    QuestRepository questRepository;
    PersonRepository personRepository;

    @Autowired
    public QuestServiceImplementation(QuestRepository questRepository, PersonRepository personRepository) {
        this.questRepository = questRepository;
        this.personRepository = personRepository;
    }


    @Override
    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    @Override
    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    @Override
    public Quest findById(long id) {
        return questRepository.findById(id);
    }


    /***
     * Mark quest as done(true) or not done(false).
     * @param idOfQuest of quest
     * @param mark to set isDone on quest, true or false;
     * @throws Exception
     */
    @Override
    public void markQuest(long idOfQuest, boolean mark) throws Exception {
        Quest quest = questRepository.findById(idOfQuest);
        if (quest == null) {
            throw new Exception();
        } else {
            quest.setIsDone(mark);
            questRepository.save(quest);
        }
    }

    /***
     * To confirm that the quest really done.
     * Will grant xp and coins to the assignee of the quest.
     * @param idOfQuest
     * @throws Exception
     */
    @Override
    public void confirmDone(long idOfQuest) throws Exception {
        Quest quest = questRepository.findById(idOfQuest);
        Child child = quest.getAssignee();
        if (quest == null || child == null) {
            throw new Exception();
        } else {
            quest.setIsConfirmed(true);
            //quest.setIsDone(false);
            child.addXp(quest.getXp());
            child.addCoins(quest.getCoins());
            questRepository.save(quest);
            personRepository.save(child);
        }
    }

    /***
     * Assign quest to a child.
     * @param idOfQuest to be assigned.
     * @param idOfChild to get the quest assigned.
     * @throws Exception
     */
    @Override
    public void assignQuest(long idOfQuest, long idOfChild) throws Exception {
        Quest quest = questRepository.findById(idOfQuest);
        Child child = personRepository.findChildById(idOfChild);
        if (quest == null || child == null) {
            throw new Exception();
        } else {
            child.addQuest(quest);
            quest.setAssignee(child);
            personRepository.save(child);
            questRepository.save(quest);
        }
    }

    @Override
    public void unassignQuest(long idOfQuest, long idOfChild) throws Exception {
        Quest quest = questRepository.findById(idOfQuest);
        Child child = personRepository.findChildById(idOfChild);
        if (quest == null || child == null) {
            throw new Exception();
        } else {
            child.removeQuest(quest);
            child.addQuest(quest);
            quest.setAssignee(null);
            personRepository.save(child);
            questRepository.save(quest);
        }
    }

    /***
     * Creates quest and saves it to the database.
     * @param quest to be saved.
     * @param idOfParent of the maker of the quest.
     * @throws Exception
     */
    @Override
    public void createQuest(Quest quest, long idOfParent) throws Exception {
        Parent parent = personRepository.findParentById(idOfParent);
        if (parent == null) {
            throw new Exception("idOfParent not in database.");
        }
        quest.setMaker(parent);
        parent.addQuest(quest);

        personRepository.save(parent);
    }
}
