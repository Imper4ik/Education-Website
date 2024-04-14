package dmytro.bozhor.universitypetprojectwebsite.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class LoginFailedException extends BadCredentialsException {

    public LoginFailedException() {
        this("Email or password was not correct!", null);
    }

    public LoginFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
