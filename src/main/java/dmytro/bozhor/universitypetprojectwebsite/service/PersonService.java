package dmytro.bozhor.universitypetprojectwebsite.service;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.exception.LoginFailedException;
import dmytro.bozhor.universitypetprojectwebsite.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person create(Person person) {
        return personRepository.save(person);
    }

    public Person findByEmail(String email) {
        return personRepository.findByEmail(email)
                .orElseThrow(LoginFailedException::new);
    }

    public boolean login(String email, String password) {
        var person = findByEmail(email);
        var loggedIn = person.getPassword().equals(password);

        if (!loggedIn) throw new LoginFailedException();

        return loggedIn;
    }

}
