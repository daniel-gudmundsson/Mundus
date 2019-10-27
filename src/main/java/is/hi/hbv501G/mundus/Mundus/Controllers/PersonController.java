package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.*;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes({"accountId", "personId"})
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //create child
    @RequestMapping("/person-test1")
    public String test1(Model model) {
        Child krakki = new Child("Jón", "123");
        personService.save(krakki);
        return "Welcome";
    }

    //Create Parent
    @RequestMapping("/person-test3")
    public String test3(Model model) {
        Parent parent = new Parent("Agnar", "123");
        personService.save(parent);
        return "Welcome";
    }

    //Get all child of parent
    @RequestMapping("/person-test5")
    public String test5(Model model) {
        Child child = new Child("Jon", "123");
        try {
            personService.assignChildToParent(child, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Welcome";
    }

    //Get all child of parent
    @RequestMapping("/person-test6")
    public String test6(Model model) {
        Parent parent = personService.findParentById(1);
        for (Child c : parent.getChildren()) {
            System.out.println(c.getName());
        }
        return "Welcome";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String loadPersons(Model model) {
        long parentId = Long.parseLong(String.valueOf(model.asMap().get("accountId")));
        Parent parent = personService.findParentById(parentId);
        Set<Child> children = parent.getChildren();
        Set<Person> persons = new HashSet<>();

        persons.addAll(children);
        persons.add(parent);

        model.addAttribute("persons", persons);
        return "persons";
    }

    @RequestMapping(value = "/pin-page", method = RequestMethod.POST)
    public String loadPinPage(@RequestParam("id") long id, Model model) {
        if (model.containsAttribute("personId")) {
            if (id == Long.parseLong(String.valueOf(model.asMap().get("personId")))) ;
            {
                return "redirect:/quests";
            }
        }
        model.addAttribute("id", id);
        return "pinPage";
    }

    @RequestMapping(value = "/pin-page-auth", method = RequestMethod.POST)
    public String authenticatePin(@RequestParam("id") long id, @RequestParam("pin") String pin, Model model) {

        Person person = personService.findPersonById(id);

        if (person == null) {
            return "redirect:/";
        }

        if (person.getPin().equals(pin)) {
            model.addAttribute("personId", person.getId());
            return "redirect:/quests";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/quests", method = RequestMethod.GET)
    public String loadPerson(Model model) {
        long personId = Long.parseLong(String.valueOf(model.asMap().get("personId")));
        Person person = personService.findPersonById(personId);
        if (person instanceof Child) {
            Child child = personService.findChildById(personId);
            Set<Quest> quests = child.getQuests();
            model.addAttribute("child", child);
            model.addAttribute("quests", quests);
            return "questViewChild";
        } else if (person instanceof Parent) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }

    }


    @RequestMapping(value = "/assignChildToParent", method = RequestMethod.POST)
    public String addChild(@RequestParam("id") long id, Model model, Child child) {
        try {
            personService.assignChildToParent(child, id);
        } catch (Exception e) {
            System.out.println("Not able to assign child to parent");
        }
        return "redirect:/profile";
    }


}
