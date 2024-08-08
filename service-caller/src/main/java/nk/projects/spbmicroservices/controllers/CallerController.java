package nk.projects.spbmicroservices.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/caller")
public class CallerController {

    private WebClient webclient;
    public CallerController(WebClient webclient){
        this.webclient = webclient;
    }
 
    @PreAuthorize("hasAuthority('SCOPE_TEST')")
    @GetMapping("/ping")
    public String ping(){
        SecurityContext context = SecurityContextHolder.getContext();
        // Authentication authentication = context.getAuthentication();
        String scopes = webclient.get().
            uri("http://localhost:3001/callme/ping")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return "Callme scopes: "+ scopes;
    }
}
