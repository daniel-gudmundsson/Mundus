package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.QuestRepository;
import is.hi.hbv501G.mundus.Mundus.Services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestServiceImplementation implements QuestService {

    QuestRepository repository;

    @Autowired
    public QuestServiceImplementation(QuestRepository questRepository){this.repository = questRepository;}



    @Override
    public Quest save(Quest quest) {
       return repository.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        repository.delete(quest);
    }

    @Override
    public List<Quest> findAll() {
        return repository.findAll();
    }

    @Override
    public Quest findById(long id) {
        return repository.findById(id);
    }
}
