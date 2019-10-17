package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Reward;
import is.hi.hbv501G.mundus.Mundus.Repositories.RewardRepository;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RewardServiceImplementation implements RewardService {


    RewardRepository rewardRepository;

    @Autowired
    public RewardServiceImplementation(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    @Override
    public Reward save(Reward quest) {
        return rewardRepository.save(quest);
    }

    @Override
    public void delete(Reward quest) {
        rewardRepository.delete(quest);
    }

    @Override
    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    @Override
    public Reward findById(long id) {
        return rewardRepository.findById(id);
    }


}
