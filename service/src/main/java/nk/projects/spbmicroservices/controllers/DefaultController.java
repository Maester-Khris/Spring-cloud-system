package nk.projects.spbmicroservices.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class DefaultController {
    
    @GetMapping
    public String sayWelcome(){
        return "Hello Devs, welcome to the WA";
    }
}
