package dmytro.bozhor.universitypetprojectwebsite.config;

import dmytro.bozhor.universitypetprojectwebsite.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersonAuthenticationManager implements AuthenticationManager {

    private final PersonService personService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        var email = (String) authentication.getPrincipal();
        var password = (String) authentication.getCredentials();

        personService.login(email, password);

        return authentication;
    }
}
