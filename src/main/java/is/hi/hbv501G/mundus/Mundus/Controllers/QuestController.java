package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
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
        Parent parent = personService.findParentById(1);
        Quest quest = new Quest("Borda godan mat", "Cool stuff", 10, 10, "123", "1234", parent);
        Quest rquest = questService.save(quest);
        System.out.println(rquest.getId());
        return "Welcome";
    }

    @RequestMapping(value = "/quest-view", method = RequestMethod.POST)
    public String loadPerson(@RequestParam("id") long id, Model model) {
        Child child = personService.findChildById(id);
        Set<Quest> quests = child.getParent().getQuests();

        model.addAttribute("child", child);
        model.addAttribute("quests", quests);
        return "questViewChild";
    }


}
