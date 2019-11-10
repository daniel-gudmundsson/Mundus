package is.hi.hbv501G.mundus.Mundus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    /**
     * @param model
     * @param session
     * @return the "home" page. If a person is logged in the homepage is the quest page
     * if an account is not logged into then the homepage is the login page
     * If an person is not logged in to a person account the the homepage is the person log in page
     */
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

