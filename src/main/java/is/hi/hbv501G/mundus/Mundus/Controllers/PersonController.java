package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.*;
import is.hi.hbv501G.mundus.Mundus.Services.AccountService;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class PersonController {

    private PersonService personService;
    private AccountService accountService;

    @Autowired
    public PersonController(PersonService personService, AccountService accountService) {
        this.personService = personService;
        this.accountService = accountService;
    }

    // Several test function
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

    /**
     * When an account has been logged into you will be redirected to the person page where you can select a person to log into
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String loadPersons(Model model, HttpSession session) {
        if (session.getAttribute("AccountIdLoggedIn") == null) {
            return "redirect:/"; // This page is not accessible unless logged into an account
        }

        long accountId = (long) session.getAttribute("AccountIdLoggedIn"); // Get the id of the person logged in
        Account account = accountService.findAccountById(accountId); // The account which is logged in
        Parent parent = account.getParent(); // Get the parent of the account
        Set<Child> children = parent.getChildren(); // Get the children of this acount
        Set<Person> persons = new HashSet<>();

        persons.addAll(children);
        persons.add(parent);

        model.addAttribute("persons", persons); // Add all the children and parent to the model
        return "persons"; // Load the person page

    }

    /**
     * When a person has been selected you must enter a pin
     * @param id
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/pin-page", method = RequestMethod.POST)
    public String loadPinPage(@RequestParam("id") long id, Model model, HttpSession session) {
        if (session.getAttribute("PersonIdLoggedIn") != null) {
            if (id == (long) session.getAttribute("PersonIdLoggedIn")) ;
            {
                return "redirect:/quests";
            }
        }
        model.addAttribute("id", id);
        return "pinPage";
    }

    /**
     * A method that authenticates a pin for a particular person
     * @param id
     * @param pin
     * @param session
     * @return
     */
    @RequestMapping(value = "/pin-page-auth", method = RequestMethod.POST)
    public String authenticatePinPost(@RequestParam("id") long id, @RequestParam("pin") String pin, HttpSession session) {

        long personId;

        try {
            personId = personService.authenticatePin(id, pin); // Try to authenticate the pin
            session.setAttribute("PersonIdLoggedIn", personId); // Then add the id to the session
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    /**
     * Loads all the information of a person
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/quests", method = RequestMethod.GET)
    public String loadPerson(Model model, HttpSession session) {
        if (session.getAttribute("PersonIdLoggedIn") == null) {
            return "redirect:/";
        }
        long personId = (long) session.getAttribute("PersonIdLoggedIn"); // Get the id of the person
        Person person = personService.findPersonById(personId); // Find the person

        if (person instanceof Child) { // If the person is a child it will se the quest page from the viewpoint of a child
            Child child = personService.findChildById(personId);
            Set<Quest> quests = child.getQuests();
            model.addAttribute("child", child);
            model.addAttribute("quests", quests); // Add the quests of the child to the model

            return "questViewChild";
        } else if (person instanceof Parent) {
            return "Welcome";
            //return "redirect:/";
        } else {
            return "redirect:/";
        }

    }

    /**
     * Assign a child to a parent
     * @param id of the parent
     * @param model
     * @param child
     * @return
     */
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
