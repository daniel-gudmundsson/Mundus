package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
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

    @Override
    public void confirmDone(long idOfQuest) throws Exception {
        Quest quest = questRepository.findById(idOfQuest);
        Child child = quest.getAssignee();
        if (quest == null || child == null) {
            throw new Exception();
        } else {
            quest.setIsConfirmed(true);
            child.addXp(quest.getXp());
            questRepository.save(quest);
            personRepository.save(child);
        }

    }

    @Override
    public void assignQuest(long questID, long assigneId) {

    }
}
