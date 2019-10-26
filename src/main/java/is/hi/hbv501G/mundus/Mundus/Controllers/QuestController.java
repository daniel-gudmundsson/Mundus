package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.*;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import is.hi.hbv501G.mundus.Mundus.Services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class QuestController {

    private QuestService questService;
    private PersonService personService;

    @Autowired
    public QuestController(QuestService questService, PersonService personService) {
        this.questService = questService;
        this.personService = personService;
    }


    @RequestMapping("/quest-test1")
    public String Home(Model model) {
        model.addAttribute(questService.findAll());
        return "quests";
    }

    @RequestMapping(value = "/quest-view", method = RequestMethod.POST)
    public String loadPerson(@RequestParam("id") long id, Model model) {
        Child child = personService.findChildById(id);
        Set<Quest> quests = child.getParent().getQuests();

        model.addAttribute("child", child);
        model.addAttribute("quests", quests);
        return "questViewChild";
    }

    @RequestMapping(value = "/markQuestAsDone", method = RequestMethod.POST)
    public String markQuestAsDone(@RequestParam("id") long id, Model model, long userID) {
        try {
            questService.markQuest(id, true);
        }
        catch (Exception e) {
            System.out.println("Not able to mark quest as done");
        }
        return "redirect:/quests";
    }

    @RequestMapping(value = "/markQuestAsConfirmed", method = RequestMethod.POST)
    public String markQuestAsConfirmed(@RequestParam("id") long id, Model model, long userID) {
        try {
            questService.confirmDone(id);
        }
        catch (Exception e) {
            System.out.println("Not able to mark quest as confirmed");
        }

        return "redirect:/quests";
    }

    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String assignQuest(@RequestParam("id") long id, Model model, long userID) {
        try {
            questService.assignQuest(id, userID);
        }
        catch (Exception e) {
            System.out.println("Not able to assign quest");
        }
        return "redirect:/quests";
    }

    @RequestMapping(value = "/deleteQuest", method = RequestMethod.POST)
    public String delete(@RequestParam("id") long id, Model model, long userID) {
        try {
            questService.delete(questService.findById(id));
        }
        catch (Exception e) {
            System.out.println("Not able to delete quest");
        }
        return "redirect:/quests";
    }


}
