package is.hi.hbv501G.mundus.Mundus.Repositories;

import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest, Long> {

    Quest save(Quest quest);
    void delete(Quest quest);
    List<Quest> findAll();
    Quest findById(long id);

    //Quest findAllByIdAndDeadlineAfter(LocalDate date);
    void deleteAllByDeadlineBefore(LocalDate date);

}
