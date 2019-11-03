package is.hi.hbv501G.mundus.Mundus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileManagementController {
    // We are not sure what we are going to do with this one ¯\_(ツ)_/¯
    @RequestMapping("/profile")
    public String home() {
        return "profile";
    }
}
