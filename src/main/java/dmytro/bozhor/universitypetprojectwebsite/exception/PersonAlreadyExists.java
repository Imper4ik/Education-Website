package dmytro.bozhor.universitypetprojectwebsite.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class PersonAlreadyExists extends BadCredentialsException {

    public PersonAlreadyExists() {
        this("Person with this email already exists!", null);
    }

    public PersonAlreadyExists(String msg, Throwable cause) {
        super(msg, cause);
    }
}
