package dmytro.bozhor.universitypetprojectwebsite.controller;

import dmytro.bozhor.universitypetprojectwebsite.config.EndpointValuesContainer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static dmytro.bozhor.universitypetprojectwebsite.config.EndpointValuesContainer.*;

@Controller
public class GlobalController {

    @GetMapping(HOME)
    @ResponseStatus(HttpStatus.OK)
    public String getHomePage() {
        return EndpointValuesContainer.getHtmlPageByName(HOME);
    }

    @GetMapping(C_PLUS_PLUS)
    @ResponseStatus(HttpStatus.OK)
    public String getCPlusPlusPage() {
        return EndpointValuesContainer.getHtmlPageByName(C_PLUS_PLUS);
    }

    @GetMapping(C_SHARP)
    @ResponseStatus(HttpStatus.OK)
    public String getCSharpPage() {
        return EndpointValuesContainer.getHtmlPageByName(C_SHARP);
    }

    @GetMapping(JAVA)
    @ResponseStatus(HttpStatus.OK)
    public String getJavaPage() {
        return EndpointValuesContainer.getHtmlPageByName(JAVA);
    }

    @GetMapping(PYTHON)
    @ResponseStatus(HttpStatus.OK)
    public String getPythonPage() {
        return EndpointValuesContainer.getHtmlPageByName(PYTHON);
    }

    @GetMapping(REGISTER)
    @ResponseStatus(HttpStatus.OK)
    public String getRegisterPage() {
        return EndpointValuesContainer.getHtmlPageByName(REGISTER);
    }

    @GetMapping(LOGIN)
    @ResponseStatus(HttpStatus.OK)
    public String getLoginPage() {
        return EndpointValuesContainer.getHtmlPageByName(LOGIN);
    }
}
