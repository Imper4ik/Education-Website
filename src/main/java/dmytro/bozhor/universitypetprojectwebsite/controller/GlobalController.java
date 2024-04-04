package dmytro.bozhor.universitypetprojectwebsite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class GlobalController {

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public String getHomePage(){
        return "/Project/home.html";
    }

    @GetMapping("/c-plus-plus")
    @ResponseStatus(HttpStatus.OK)
    public String getCPlusPlusPage(){
        return "/c-plus-plus.html";
    }

    @GetMapping("/c-sharp")
    @ResponseStatus(HttpStatus.OK)
    public String getCSharpPage(){
        return "/c-sharp.html";
    }

    @GetMapping("/java")
    @ResponseStatus(HttpStatus.OK)
    public String getJavaPage(){
        return "/java.html";
    }

    @GetMapping("/python")
    @ResponseStatus(HttpStatus.OK)
    public String getPythonPage(){
        return "/python.html";
    }
}
