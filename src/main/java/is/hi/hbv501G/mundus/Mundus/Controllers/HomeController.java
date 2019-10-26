package is.hi.hbv501G.mundus.Mundus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("accountId")
public class HomeController {
    @RequestMapping("/")
    public String home(Model model){
        if(model.containsAttribute("accountId")){
            return "redirect:/persons";
        }else{
            return "redirect:/login";
        }
    }
    }

