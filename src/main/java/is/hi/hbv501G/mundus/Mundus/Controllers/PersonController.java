package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Child;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
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
        Child krakki = new Child("Jón", "123", 123, 123, null);
        personService.save(krakki);
        return "Welcome";
    }

    //Add quest to child
    @RequestMapping("/person-test2")
    public String test2(Model model) {
        System.out.println(personService.assignQuestToChild(3,6));
        return "Welcome";
    }

    //Create Parent
    @RequestMapping("/person-test3")
    public String test3(Model model) {
        Parent parent = new Parent("Agnar","123");
        personService.save(parent);
        return "Welcome";
    }

    //Add children to parent
    @RequestMapping("/person-test4")
    public String test4(Model model) {
        System.out.println(personService.assignChildToParent(2,1));
        return "Welcome";
    }

    //Get all child of parent
    @RequestMapping("/person-test5")
    public String test5(Model model) {
        Parent parent = personService.findParentById(1);
        for (Child c : parent.getChildren()) {
            System.out.println(c.getName());
        }
        return "Welcome";
    }


}
