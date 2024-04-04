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
        return "/Project/Home.html";
    }

    @GetMapping("/c-plus-plus")
    @ResponseStatus(HttpStatus.OK)
    public String getCPlusPlusPage(){
        return "/C++.html";
    }

    @GetMapping("/c-sharp")
    @ResponseStatus(HttpStatus.OK)
    public String getCSharpPage(){
        return "/CSharp.html";
    }

    @GetMapping("/java")
    @ResponseStatus(HttpStatus.OK)
    public String getJavaPage(){
        return "/Java.html";
    }

    @GetMapping("/python")
    @ResponseStatus(HttpStatus.OK)
    public String getPythonPage(){
        return "/Python.html";
    }
}
