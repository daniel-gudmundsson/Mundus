package is.hi.hbv501G.mundus.Mundus.Services.Implementation;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Reward;
import is.hi.hbv501G.mundus.Mundus.Repositories.PersonRepository;
import is.hi.hbv501G.mundus.Mundus.Repositories.RewardRepository;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public boolean purchaseReward(long rewardId, long buyerId) throws Exception{

        Child child = personRepository.findChildById(buyerId);
        if (child == null) {
            throw new Exception();
        }
        else {
            int childLVL = child.getLevel();
            int childCoins = child.getTotalCoins();
            Reward reward = findById(rewardId);
            int requiredLVL = reward.getLevelRequired();
            int price = reward.getPrice();
            if (childLVL >= requiredLVL && childCoins >= price) {
                //Reward reward = rewardRepository.findById(rewardId);
                child.addReward(rewardId); // Adds the rewardId to the child.

                child.setTotalCoins(childCoins - price);
                personRepository.save(child); // Saves the child after it has been updated
                return true;
            }
        }
            return false;
    }


    /**
     * An implmentation of creating a new reward
     *
     * @param reward
     * @param parentId
     * @throws Exception
     */
    @Override
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
    @Override
    public Set<Reward> getChildRewardAvailable(long childId) throws Exception {
        Child child = personRepository.findChildById(childId);
        if(child == null){
            throw new Exception();
        }
        Set<Reward> allRewards = new HashSet<>(child.getParent().getRewards());
        List<Long> childRewardIds = child.getReward();
        for (long id : childRewardIds) {
            allRewards.remove(findById(id));
        }
        return allRewards;
    }
    @Override
    public Set<Reward> getChildRewards(long childId) throws Exception {
        Child child = personRepository.findChildById(childId);
        if(child == null){
            throw new Exception();
        }
        Set<Reward> allRewards = new HashSet<>(child.getParent().getRewards());
        System.out.println(allRewards);
        Set<Reward> rewardsNotOwned = getChildRewardAvailable(childId);
        System.out.println(rewardsNotOwned);
        allRewards.removeAll(rewardsNotOwned);
        return allRewards;
    }

    @Override
    public void deleteReward(long parentId, long rewardId) throws Exception {
        Parent parent = personRepository.findParentById(parentId);
        for(Child child : parent.getChildren()){
                if(child.getReward().contains(rewardId)){
                    child.removeReward(rewardId);
            }
        }
        Reward reward = rewardRepository.findById(rewardId);
        parent.removeReward(reward);
        Parent parentReturn = personRepository.save(parent);
        if(parentReturn == null){
            throw new Exception("Save failed");
        }
    }


}
