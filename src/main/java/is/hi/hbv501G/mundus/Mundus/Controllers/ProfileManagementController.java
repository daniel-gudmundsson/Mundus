package is.hi.hbv501G.mundus.Mundus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileManagementController {

    @RequestMapping("/profile")
    public String home(){
        return "profile";
    }
}
