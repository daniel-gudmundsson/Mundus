package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.*;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.hbv501G.mundus.Mundus.Services.RewardService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

    /**
     * @param model
     * @return Returns the reward page (marketplace)
     */
    @RequestMapping("/marketplace")
    public String rewardHome(Model model, HttpSession session, Reward reward) {//, long userID){
        if (session.getAttribute("PersonIdLoggedIn") == null) {
            return "redirect:/";
        }
        long personId = (long) session.getAttribute("PersonIdLoggedIn"); // Get the id of the person
        Person person = personService.findPersonById(personId); // Find the person

        if (person instanceof Child) { // If the person is a child it will se the quest page from the viewpoint of a child
            Set<Reward> rewardsAvailable = null;
            Set<Reward> ownRewards = null;
            try {
                Child child = personService.findChildById(personId);
                rewardsAvailable = rewardService.getChildRewardAvailable(personId);
                ownRewards = rewardService.getChildRewards(personId);
                model.addAttribute("child", child);
                model.addAttribute("rewards",rewardsAvailable); // Add the quests of the child to the model
                model.addAttribute("ownrewards",ownRewards);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "marketplaceChild";
        } else if (person instanceof Parent) {
            try {
                Parent parent = personService.findParentById(personId);
                Set<Pair<Child, Reward>> rewardPairs = rewardService.getPurchasedRewards(personId);
                System.out.println(rewardPairs);
                model.addAttribute("parent", parent);
                model.addAttribute("purchasedRewards", rewardPairs);
                model.addAttribute("allRewards", parent.getRewards());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "marketplaceParent";
        } else {
            return "redirect:/";
        }
        //Child child = personService.findChildById(userID);
//        Child child = personService.findChildById(4);
//        Set<Reward> allRewards = child.getParent().getRewards();
//        List<Long> childRewardIds = child.getReward();
//        for (long id : childRewardIds) {
//            allRewards.remove(rewardService.findById(id));
//        }
//        model.addAttribute("rewards", allRewards);
    }

    public void getChildQuestNoOwned(Set<Reward> allRewards, List<Long> childRewardIds) {
        for (long id : childRewardIds) {
            allRewards.remove(rewardService.findById(id));
        }
    }

    /**
     * POST method for creating a new reward
     *
     * @param reward  The reward created
     * @param result
     * @param model
     * @param session to see who is creating it
     * @return
     */
    @RequestMapping(value = "/createReward", method = RequestMethod.POST)
    public String createRewardPOST(@Valid Reward reward, BindingResult result, Model model, HttpSession session) {//, long userID){
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "redirect:/marketplace";
        }
        long parentId = (long) session.getAttribute("PersonIdLoggedIn"); // Get the id of the parent creating this reward
        //Parent maker = personService.findParentById(parentId);
        //reward.setMaker(maker);
        try {
            rewardService.createReward(reward, parentId); // Create a new reward
        } catch (Exception e) {
            return "redirect:/marketplace";
        }
        return "redirect:/marketplace";
    }

    /**
     * GET method for creating a reward
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/createReward", method = RequestMethod.GET)
    public String createRewardGET(Model model, HttpSession session) {
        if (session.getAttribute("PersonIdLoggedIn") != null) {
            long personId = (long) session.getAttribute("PersonIdLoggedIn"); // Get the id of the parent creating this reward
            Person person = personService.findPersonById(personId); // Find the type of person who is loading the reward page.

            if (person instanceof Child) {
                return "redirect:/"; // A child can't create rewards, only a parent can
            } else {
                model.addAttribute("reward", new Reward()); // Add the new reward to the model
                return "createReward";
            }
        }
        return "redirect:/";
    }


    /**
     * @param rewardId The id of the reward that is to be deleted
     * @param model
     * @param userID   !!!!! TODO, don't have to use this probably
     * @return
     */
    @RequestMapping(value = "/deletereward", method = RequestMethod.POST)
    public String deleteReward(@RequestParam("id") long rewardId, HttpSession session) {
        if (session.getAttribute("PersonIdLoggedIn") != null) {
            long parentId = (long) session.getAttribute("PersonIdLoggedIn");
            try {
                rewardService.deleteReward(parentId,rewardId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/marketplace";
        }
        return "redirect:/";

    }

    /**
     * A method for purchasing a reward
     *
     * @param rewardId Id of the reward to be purchased
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/purchaseReward", method = RequestMethod.POST)
    public String purchaseReward(@RequestParam("id") long rewardId, Model model, HttpSession session) {
        // Reward reward = rewardService.findById(id);//.orElseThrow(() -> new IllegalArgumentException("Invalid reward ID"));
        long childId = (long) session.getAttribute("PersonIdLoggedIn"); // Get the id of the buyer

            try {
                boolean success = rewardService.purchaseReward(rewardId, childId); // Purchase the reward for the child
            }
            catch (Exception e) {
                return "redirect:/rewards";
            }

            //TODO Bregðast við success
        return "redirect:/marketplace";
    }

    @RequestMapping(value = "/grantReward", method = RequestMethod.POST)
    public String grantReward(@RequestParam("rewardId") long rewardId,@RequestParam("childId") long childId) {
        try {
            rewardService.grantReward(rewardId, childId);
        }
        catch (Exception e) {
            return "redirect:/marketplace";
        }

        //TODO Bregðast við success
        return "redirect:/marketplace";
    }




//    /**
//     * Just a testing function
//     * @param model
//     * @return
//     */
//    @RequestMapping("/reward-test1")
//    public String test1(Model model) {
//        String name = "Lollipop";
//        String description = "Delicious lollipop with vanilla and strawberry flavor";
//        int price = 1337;
//        int levelRequired = 3;
//        boolean autorenew = true;
//        LocalDate endDate = LocalDate.now();
//        boolean visible = true;
//        Parent maker = personService.findParentById(2);
//        System.out.println("asgasdgklasghasdgjashkghasgahskjgashgjkashgjkshgjksahgsakjghskjdghskgsahdgsja");
//        System.out.println(maker);
//        Reward reward = new Reward(name, description, price, levelRequired, autorenew, endDate, maker);
//        maker.addReward(reward);
//        personService.save(maker);
//        rewardService.save(reward);
//        return "Welcome";
//    }


}
