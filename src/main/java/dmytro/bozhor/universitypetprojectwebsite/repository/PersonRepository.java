package dmytro.bozhor.universitypetprojectwebsite.repository;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

}
