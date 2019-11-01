package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Reward;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RewardController {

    private RewardService rewardService;
    private PersonService personService;

    @Autowired
    public RewardController(PersonService personService, RewardService rewardService) {
        this.personService = personService;
        this.rewardService = rewardService;
    }


    @RequestMapping("/marketplace")
    public String rewardHome(Model model){//, long userID){
        //Child child = personService.findChildById(userID);
        Child child = personService.findChildById(4);
        Set<Reward> allRewards= child.getParent().getRewards();
        List<Long> childRewardIds = child.getReward();
        for (long id: childRewardIds) {
            allRewards.remove(rewardService.findById(id));
        }
        model.addAttribute("rewards",allRewards);
        return "marketplace";
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

    @RequestMapping("/reward-test1")
    public String test1(Model model) {
        String name = "Lollipop";
        String description = "Delicious lollipop with vanilla and strawberry flavor";
        int price = 1337;
        int levelRequired = 3;
        boolean autorenew = true;
        LocalDate endDate = LocalDate.now();
        boolean visible = true;
        Parent maker = personService.findParentById(2);
        System.out.println("asgasdgklasghasdgjashkghasgahskjgashgjkashgjkshgjksahgsakjghskjdghskgsahdgsja");
        System.out.println(maker);
        Reward reward = new Reward(name, description, price, levelRequired, autorenew, endDate, visible, maker);
        maker.addReward(reward);
        personService.save(maker);
        rewardService.save(reward);
        return "Welcome";
    }






}
