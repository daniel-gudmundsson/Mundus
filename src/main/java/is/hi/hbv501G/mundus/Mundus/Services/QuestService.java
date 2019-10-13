package is.hi.hbv501G.mundus.Mundus.Services;


import is.hi.hbv501G.mundus.Mundus.Entities.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestService {


    Quest save(Quest quest);
    void delete(Quest quest);
    List<Quest> findAll();
    Quest findById(long id);
}
