package dmytro.bozhor.universitypetprojectwebsite;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.repository.PersonRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class GlobalControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;

    @SneakyThrows
    @Test
    void testRegister_shouldRegisterPerson_whenProvidedValidData() {

        //given
        var person = Person.builder()
                .username("Dimond Diller")
                .email("dimaastop1@gmail.com")
                .password("1378942gggfv&**")
                .build();

        //when
        var request = post("/register")
                .param("username", person.getUsername())
                .param("email", person.getEmail())
                .param("password", person.getPassword());

        mockMvc.perform(request)
                .andExpect(status().isFound());

        //then
        var savedPersonPredicate = personRepository.findByEmail(person.getEmail());
        assertThat(savedPersonPredicate).isPresent();

        var savedPerson = savedPersonPredicate.get();
        assertThat(savedPerson.getUsername()).isEqualTo(person.getUsername());
        assertThat(savedPerson.getEmail()).isEqualTo(person.getEmail());
    }
}
