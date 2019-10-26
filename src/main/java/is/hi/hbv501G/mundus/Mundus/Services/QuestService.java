package is.hi.hbv501G.mundus.Mundus.Services;



import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
import java.util.List;
import java.util.Optional;

public interface QuestService {


    Quest save(Quest quest);
    void delete(Quest quest);
    List<Quest> findAll();
    Quest findById(long id);

    /**
     *Marks the quest with a mark, if it is done or not;
     * @param id of quest
     * @param mark to set isDone on quest, true or false;
     * @return
     */
    void markQuest(long idOfQuest, boolean mark) throws Exception;

    /**
     *
     * @param idOfQuest
     */
    void confirmDone(long idOfQuest) throws Exception;

    void assignQuest(long questID, long assigneId) throws Exception;


}
