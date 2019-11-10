package is.hi.hbv501G.mundus.Mundus.Services;

import is.hi.hbv501G.mundus.Mundus.Entities.Reward;

import java.util.List;
import java.util.Set;

public interface RewardService {

    Reward save(Reward quest);
    void delete(Reward quest);
    List<Reward> findAll();
    Reward findById(long id);
    boolean purchaseReward(long rewardId, long byuerId) throws Exception;

    void createReward(Reward reward, long parentId) throws Exception;
    Set<Reward> getChildRewardAvailable(long childId) throws Exception;
    Set<Reward> getChildRewards(long childId) throws Exception;
    public void deleteReward(long parentId, long rewardId) throws Exception;



}
