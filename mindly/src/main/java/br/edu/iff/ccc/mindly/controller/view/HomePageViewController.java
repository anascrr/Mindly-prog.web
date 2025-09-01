package br.edu.iff.ccc.mindly.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageViewController {

    @GetMapping("/home")
    public String homePage() {
        return "index"; 
    }
}
