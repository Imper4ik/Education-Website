package dmytro.bozhor.universitypetprojectwebsite.service;

import dmytro.bozhor.universitypetprojectwebsite.config.Role;
import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.exception.LoginFailedException;
import dmytro.bozhor.universitypetprojectwebsite.exception.PersonAlreadyExists;
import dmytro.bozhor.universitypetprojectwebsite.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public Person create(Person person) {

        if (personRepository.findByEmail(person.getEmail()).isPresent()) throw new PersonAlreadyExists();

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var person = personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found"));

        return new User(
                person.getEmail(),
                person.getPassword(),
                Collections.singletonList(person.getRole()));
    }
}
