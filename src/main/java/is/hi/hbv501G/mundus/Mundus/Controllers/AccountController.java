package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Account;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import is.hi.hbv501G.mundus.Mundus.Services.AccountService;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;


@Controller
public class AccountController {

    private AccountService accountService;
    private PersonService personService;

    @Autowired
    public AccountController(AccountService accountService, PersonService personService) {
        this.accountService = accountService;
        this.personService = personService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) {
        long accountId;

        try {
            accountId = accountService.login(email, password);
            session.setAttribute("AccountIdLoggedIn", accountId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logoutAccount", method = RequestMethod.GET)
    public String logOutAccount(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/logoutPerson", method = RequestMethod.GET)
    public String logOutPerson(HttpSession session) {
        if (session.getAttribute("PersonIdLoggedIn") != null) {
            session.removeAttribute("PersonIdLoggedIn");
        }
        return "redirect:/";
    }



    @RequestMapping("/test1")
    public String home(Model model) {
        if (model.containsAttribute("accountId")) {
            System.out.println(Long.parseLong(String.valueOf(model.asMap().get("accountId"))));
        } else {
            return "redirect:/login";
        }
        return "Welcome";
    }

    @RequestMapping("/test3")
    public String test3() {
        Account account = new Account("Agnar", "agnar-97@hotmail.com", "mamma", LocalDate.now());
        Parent parent = new Parent("Agnar", "1234");
        try {
            accountService.createAccount(account, parent);
        } catch (Exception e) {

        }
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid Account account, BindingResult result, Model model){
        if(result.hasErrors()){
            System.out.println("fail");
            return "signup";
        }
        Account exists = accountService.findAccountByEmail(account.getEmail());
        if(exists == null){
            System.out.println(account.getName());
            System.out.println(account.getEmail());
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(Account account){
        return "signup";
    }



}
