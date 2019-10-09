package is.hi.hbv501G.mundus.Mundus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "Welcome";
    }
}
