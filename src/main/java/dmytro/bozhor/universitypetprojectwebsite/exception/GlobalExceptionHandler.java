package dmytro.bozhor.universitypetprojectwebsite.exception;

import dmytro.bozhor.universitypetprojectwebsite.util.ControllerDispatcherUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static dmytro.bozhor.universitypetprojectwebsite.util.EndpointValuesContainer.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(LoginFailedException.class)
    public String handle() {
        return ControllerDispatcherUtil.sendRedirectWithError(LOGIN);
    }

}
