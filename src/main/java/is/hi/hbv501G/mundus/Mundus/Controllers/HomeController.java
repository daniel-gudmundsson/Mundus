package is.hi.hbv501G.mundus.Mundus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Model model, HttpSession session) {

        if (session.getAttribute("AccountIdLoggedIn") != null) {
            if (session.getAttribute("PersonIdLoggedIn") != null) {
                return "redirect:/quests";
            } else {
                return "redirect:/persons";
            }
        } else {
            return "redirect:/login";
        }
    }
}

