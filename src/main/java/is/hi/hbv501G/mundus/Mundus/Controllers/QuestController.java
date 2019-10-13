package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import is.hi.hbv501G.mundus.Mundus.Services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestController {

    private QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }


    @RequestMapping("/quest-test1")
    public String Home(Model model){
        Quest quest = new Quest("Borda godan mat","Cool stuff",10,10,"123","1234",null,false);
        Quest rquest = questService.save(quest);
        System.out.println(rquest.getId());
        return "Welcome";
    }

}
