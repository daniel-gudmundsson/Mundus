package is.hi.hbv501G.mundus.Mundus.Services;

import is.hi.hbv501G.mundus.Mundus.Entities.Reward;

import java.util.List;

public interface RewardService {

    Reward save(Reward quest);
    void delete(Reward quest);
    List<Reward> findAll();
    Reward findById(long id);



}
