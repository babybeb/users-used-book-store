package babybeb.usersusedbookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {
    @GetMapping("/")
    public String home(){
        log.info("main controller");
        return "home.html";
    }
}