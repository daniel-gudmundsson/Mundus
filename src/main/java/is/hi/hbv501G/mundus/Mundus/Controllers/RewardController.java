package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Reward;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RewardController {

    private RewardService rewardService;
    private PersonService personService;

    @RequestMapping("/rewards")
    public String rewardHome(Model model, long userID){
        Child child = personService.findChildById(userID);
        Set<Reward> allRewards= child.getParent().getRewards();
        List<Long> childRewardIds = child.getReward();
        for (long id: childRewardIds) {
            allRewards.remove(rewardService.findById(id));
        }
        model.addAttribute("rewards",allRewards);
        return "rewards";
    }

    @RequestMapping(value ="/addreward", method = RequestMethod.POST)
    public String addReward(@Valid Reward reward, BindingResult result, Model model, long userID){
        if(result.hasErrors()){
            return "add-reward";
        }
        rewardService.save(reward);
        return "redirect:/rewards";
    }

    @RequestMapping(value = "/deletereward", method = RequestMethod.POST)
    public String deleteReward(@RequestParam("id") long id, Model model, long userID) {
        Reward reward = rewardService.findById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid reward ID"));
        rewardService.delete(reward);
        return "redirect:/rewards";
    }

    @RequestMapping(value = "/purchaseReward", method = RequestMethod.POST)
    public String purchaseReward(@RequestParam("id") long rewardId, Model model, long buyerId) {
        // Reward reward = rewardService.findById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid reward ID"));
        rewardService.purchaseReward(rewardId, buyerId);
        return "redirect:/rewards";
    }







}
