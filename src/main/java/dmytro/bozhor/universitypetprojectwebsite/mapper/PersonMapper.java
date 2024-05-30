package dmytro.bozhor.universitypetprojectwebsite.mapper;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.util.SecurityRolesHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class PersonMapper {

    public Person map(String username, String email, String password) {
        return Person.builder()
                .username(username)
                .email(email)
                .password(password)
                .roles(Collections.singletonList(SecurityRolesHolder.USER))
                .build();
    }

}
