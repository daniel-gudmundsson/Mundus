package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Reward;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.RewardRepository;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    /**
     * An implementation of buying a reward
     *
     * @param rewardId
     * @param buyerId
     */
    @Override
    public void purchaseReward(long rewardId, long buyerId) throws Exception {
        Child child = personRepository.findChildById(buyerId);
        if (child == null) {
            throw new Exception();
        } else {
            //Reward reward = rewardRepository.findById(rewardId);
            child.addReward(rewardId); // Adds the rewardId to the child.
            /*if (!reward.isAutorenew()) {
                reward.setAutorenew(false);
            }*/
            personRepository.save(child); // Saves the child after it has been updated
        }
    }


    /**
     * An implmentation of creating a new reward
     *
     * @param reward
     * @param parentId
     * @throws Exception
     */
    public void createReward(Reward reward, long parentId) throws Exception {
        Parent parent = personRepository.findParentById(parentId);
        if (parent == null) {
            throw new Exception();
        } else {
            reward.setMaker(parent);
            parent.addReward(reward);
            //rewardRepository.save(reward);
            personRepository.save(parent);
        }
    }


}
