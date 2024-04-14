package dmytro.bozhor.universitypetprojectwebsite.domain;

import dmytro.bozhor.universitypetprojectwebsite.config.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = {"email"}))
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
