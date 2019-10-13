package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import is.hi.hbv501G.mundus.Mundus.Entities.Quest;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //create child
    @RequestMapping("/person-test1")
    public String test1(Model model) {
        Child krakki = new Child("Jón", "123", 123, 123);
        krakki.addQuest(new Quest());
        personService.save(krakki);
        return "Welcome";
    }

    //Add quest to child
    @RequestMapping("/person-test2")
    public String test12(Model model) {
        System.out.println(personService.assignQuestToChild(3,6));
        return "Welcome";
    }


}
