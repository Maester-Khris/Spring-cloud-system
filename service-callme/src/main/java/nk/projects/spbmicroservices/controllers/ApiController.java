package nk.projects.spbmicroservices.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiController {
    private List<String> users = new ArrayList<String>(Arrays.asList("Jose","Frank","Fomo","Helene","Eugène"));
 
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello from the micro services";
    }
    @GetMapping
    public List<String> getAllUser() {
        return users;
    }
    @PostMapping("/{user}")
    public String postMethodName(@PathVariable String user) {
        users.add(user);
        return "User created: "+user;
    }
    @PatchMapping("/{userid}")
    public ResponseEntity<List<String>> updateUser(@PathVariable String userid, @RequestBody Userdata userdata){
        System.out.println("data received from json: "+userdata.toString());
        int uid = Integer.parseInt(userid);
        if(uid>0 && uid<=users.size()){
            if(!users.contains(userdata.name)){
                users.set(Integer.parseInt(userid), userdata.name);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userdata.getName())
                    .toUri();
                return ResponseEntity.created(uri).body(users);
            }
            return ResponseEntity.badRequest().body(users);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

class Userdata {
    String name;
    String description;


    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}