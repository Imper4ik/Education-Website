package dmytro.bozhor.universitypetprojectwebsite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.exception.PersonAlreadyExists;
import dmytro.bozhor.universitypetprojectwebsite.repository.PersonRepository;
import dmytro.bozhor.universitypetprojectwebsite.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testCreate_shouldCreateNewPerson_whenValidPersonDataProvided() {

        // given
        var person = Person.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password123")
                .build();
        var encodedPassword = "encodedPassword";

        doReturn(Optional.empty()).when(personRepository).findByEmail(person.getEmail());
        doReturn(encodedPassword).when(passwordEncoder).encode(person.getPassword());
        doReturn(person).when(personRepository).save(person);

        // when
        var savedPerson = personService.create(person);

        // then
        assertNotNull(savedPerson);
        assertEquals(person.getEmail(), savedPerson.getEmail());
        assertEquals(encodedPassword, savedPerson.getPassword());

        verify(personRepository).findByEmail(any(String.class));
        verify(passwordEncoder).encode(any(String.class));
        verify(personRepository).save(person);
    }

    @Test
    public void testCreate_shouldThrowPersonAlreadyExists_whenInvalidPersonDataProvided() {

        // given
        var person = Person.builder()
                .username("testUser")
                .email("test@example.com")
                .password("password123")
                .build();

        doReturn(Optional.of(Person.builder().build())).when(personRepository).findByEmail(person.getEmail());

        // when
        assertThrows(PersonAlreadyExists.class, () -> personService.create(person));

        // then
        verify(personRepository).findByEmail(any(String.class));
        verify(passwordEncoder, never()).encode(any(String.class));
        verify(personRepository, never()).save(person);
    }
}