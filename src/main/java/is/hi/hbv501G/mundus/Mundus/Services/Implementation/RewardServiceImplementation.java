package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Reward;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.RewardRepository;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RewardServiceImplementation implements RewardService {


    RewardRepository rewardRepository;
    PersonRepository personRepository;

    @Autowired
    public RewardServiceImplementation(RewardRepository rewardRepository, PersonRepository personRepository) {
        this.rewardRepository = rewardRepository;
        this.personRepository = personRepository;
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

    @Override
    public void purchaseReward(long rewardId, long buyerId) {
        Child child = personRepository.findChildById(buyerId);
        Reward reward = rewardRepository.findById(rewardId);
        child.addReward(rewardId);
        if (!reward.isAutorenew()) {
            reward.setAutorenew(false);
        }
        personRepository.save(child);
    }


    public void createReward(Reward reward, long parentId) throws Exception {
        Parent parent = personRepository.findParentById(parentId);
        if (parent == null){
            throw new Exception();
        }else{
            reward.setMaker(parent);
            parent.addReward(reward);

            personRepository.save(parent);
            rewardRepository.save(reward);
        }
    }


}
