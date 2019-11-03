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

import java.text.SimpleDateFormat;
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


    /**
     * A function for marking a quest as done
     * @param questId
     * @param model
     * @return
     */
    @RequestMapping(value = "/markQuestAsDone", method = RequestMethod.POST)
    public String markQuestAsDone(@RequestParam("id") long questId, Model model) {
        try {
            questService.markQuest(questId, true);
        }
        catch (Exception e) {
            System.out.println("Not able to mark quest as done");
        }
        return "redirect:/quests";
    }

    /**
     * Marks quest as confirmed. (After the child has marked a quest as finished the parent must confirm it)
     * @param questId
     * @param model
     * @return
     */
    @RequestMapping(value = "/markQuestAsConfirmed", method = RequestMethod.POST)
    public String markQuestAsConfirmed(@RequestParam("id") long questId, Model model) {
        try {
            questService.confirmDone(questId);
        }
        catch (Exception e) {
            System.out.println("Not able to mark quest as confirmed");
        }

        return "redirect:/quests";
    }

    /**
     * A method for assigning a quest to a child
     * @param questId
     * @param model
     * @param childId
     * @return
     */
    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String assignQuest(@RequestParam("id") long questId, Model model, long childId) {
        try {
            questService.assignQuest(questId, childId);
        }
        catch (Exception e) {
            System.out.println("Not able to assign quest");
        }
        return "redirect:/quests";
    }

    /**
     * A method for deleting a quest
     * @param questId
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteQuest", method = RequestMethod.POST)
    public String delete(@RequestParam("id") long questId, Model model) {
        try {
            questService.delete(questService.findById(questId));
        }
        catch (Exception e) {
            System.out.println("Not able to delete quest");
        }
        return "redirect:/quests";
    }


}
