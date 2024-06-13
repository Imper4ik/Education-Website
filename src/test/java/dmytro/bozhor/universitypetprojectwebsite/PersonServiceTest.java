package dmytro.bozhor.universitypetprojectwebsite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.exception.PersonAlreadyExists;
import dmytro.bozhor.universitypetprojectwebsite.repository.PersonRepository;
import dmytro.bozhor.universitypetprojectwebsite.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;
    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        personService = new PersonService(personRepositoryMock, passwordEncoderMock);
    }

    @Test
    public void testCreateNewPerson() {
        // Arrange
        Person person = new Person();
        person.setEmail("test@example.com");
        person.setPassword("password123");

        when(personRepositoryMock.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoderMock.encode("password123")).thenReturn("encodedPassword");

        // Act
        Person savedPerson = personService.create(person);

        // Assert
        assertNotNull(savedPerson);
        assertEquals("test@example.com", savedPerson.getEmail());
        assertEquals("encodedPassword", savedPerson.getPassword());
        verify(personRepositoryMock, times(1)).findByEmail("test@example.com");
        verify(passwordEncoderMock, times(1)).encode("password123");
        verify(personRepositoryMock, times(1)).save(person);
    }

    @Test
    public void testCreateExistingPerson() {
        // Arrange
        Person existingPerson = new Person();
        existingPerson.setEmail("existing@example.com");
        existingPerson.setPassword("existingPassword");

        when(personRepositoryMock.findByEmail("existing@example.com")).thenReturn(Optional.of(existingPerson));

        // Act and Assert
        assertThrows(PersonAlreadyExists.class, () -> {
            Person person = new Person();
            person.setEmail("existing@example.com");
            person.setPassword("password123");
            personService.create(person);
        });

        verify(personRepositoryMock, times(1)).findByEmail("existing@example.com");

    }
}