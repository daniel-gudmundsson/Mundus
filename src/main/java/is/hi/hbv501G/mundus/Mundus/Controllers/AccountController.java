package is.hi.hbv501G.mundus.Mundus.Controllers;

import is.hi.hbv501G.mundus.Mundus.Entities.Account;
import is.hi.hbv501G.mundus.Mundus.Entities.Parent;
import is.hi.hbv501G.mundus.Mundus.Entities.Person;
import is.hi.hbv501G.mundus.Mundus.Services.AccountService;
import is.hi.hbv501G.mundus.Mundus.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@SessionAttributes("accountId")
public class AccountController {

    private AccountService accountService;
    private PersonService personService;

    @Autowired
    public AccountController(AccountService accountService, PersonService personService) {
        this.accountService = accountService;
        this.personService = personService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        System.out.println(email);
        System.out.println(password);
        Account account = accountService.findAccountByEmail(email);

        if(account == null){return "redirect:/";}

        if(account.getPassword().equals(password)){
            model.addAttribute("accountId",account.getId());
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }



    @RequestMapping("/test1")
    public String home(Model model){
        if(model.containsAttribute("accountId")){
            System.out.println(Long.parseLong(String.valueOf(model.asMap().get("accountId"))));
        }else{
            return "redirect:/login";
        }
        return "Welcome";
    }

    @RequestMapping("/test3")
    public String test3(){
        Account account = new Account("Agnar", "agnar-97@hotmail.com","mamma", LocalDate.now());
        Parent parent = new Parent("Agnar", "1234");
        try {
            accountService.createAccount(account, parent);
        }catch (Exception e){

        }
        return "login";
    }



}
