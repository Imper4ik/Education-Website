package dmytro.bozhor.universitypetprojectwebsite.mapper;

import dmytro.bozhor.universitypetprojectwebsite.config.Role;
import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person map(String username, String email, String password) {
        return Person.builder()
                .username(username)
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
    }

}
