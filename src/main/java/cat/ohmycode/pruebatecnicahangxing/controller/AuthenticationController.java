package cat.ohmycode.pruebatecnicahangxing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/forbidden")
    public String forbidden() {
        return "forbidden";
    }

}
