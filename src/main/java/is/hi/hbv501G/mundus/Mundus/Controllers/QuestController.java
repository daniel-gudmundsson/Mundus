package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.*;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import is.hi.hbv501G.mundus.Mundus.Services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
     *
     * @param questId
     * @param model
     * @return
     */
    @RequestMapping(value = "/markQuestAsDone", method = RequestMethod.POST)
    public String markQuestAsDone(@RequestParam("id") long questId, Model model) {
        try {
            questService.markQuest(questId, true);
        } catch (Exception e) {
            System.out.println("Not able to mark quest as done");
        }
        return "redirect:/quests";
    }

    /**
     * Marks quest as confirmed. (After the child has marked a quest as finished the parent must confirm it)
     *
     * @param questId
     * @param model
     * @return
     */
    @RequestMapping(value = "/markQuestAsConfirmed", method = RequestMethod.POST)
    public String markQuestAsConfirmed(@RequestParam("id") long questId, Model model) {
        try {
            questService.confirmDone(questId);
        } catch (Exception e) {
            System.out.println("Not able to mark quest as confirmed");
        }

        return "redirect:/quests";
    }

    /**
     * A method for assigning a quest to a child
     *
     * @param questId
     * @param model
     * @param childId
     * @return
     */
    @RequestMapping(value = "/assignQuest", method = RequestMethod.POST)
    public String assignQuest(@RequestParam("id") long questId, Model model, long childId) {
        try {
            questService.assignQuest(questId, childId);
        } catch (Exception e) {
            System.out.println("Not able to assign quest");
        }
        return "redirect:/quests";
    }

    /**
     * A method for deleting a quest
     *
     * @param questId
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteQuest", method = RequestMethod.POST)
    public String delete(@RequestParam("id") long questId, Model model) {
        try {
            questService.delete(questService.findById(questId));
        } catch (Exception e) {
            System.out.println("Not able to delete quest");
        }
        return "redirect:/quests";
    }

    /***
     * A post method for createing a quest.
     * @param quest
     * @param result
     * @param model
     * @param session
     * @param childId
     * @return
     */
    @RequestMapping(value = "/createQuest", method = RequestMethod.POST)
    public String createQuestPOST(@Valid Quest quest, BindingResult result, Model model, HttpSession session, @RequestParam("childId") long childId) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "createQuest";
        }


        try {
            long idOfParent = (long) session.getAttribute("PersonIdLoggedIn");
            questService.createQuest(quest, idOfParent);

            //Not finished
//            if(childId != -1){
//                questService.assignQuest(quest.getId(), childId);
//            }
            //

        } catch (Exception e) {
            return "createQuest";
        }

        return "redirect:/";
    }

    /***
     * GET method for creating a quest.
     * Will load create page with form if parent is logged in.
     * @param quest
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/createQuest", method = RequestMethod.GET)
    public String createQuestGET(Quest quest, HttpSession session, Model model) {
        if (session.getAttribute("PersonIdLoggedIn") != null) {
            long personId = (long) session.getAttribute("PersonIdLoggedIn");
            Person person = personService.findPersonById(personId);

            if (person instanceof Child) {
                return "redirect:/";
            } else {
                Parent parent = (Parent) person;
                model.addAttribute("children", parent.getChildren());
                return "createQuest";
            }
        }
        return "redirect:/";
    }


}
