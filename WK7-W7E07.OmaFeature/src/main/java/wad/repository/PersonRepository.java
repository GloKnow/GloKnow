package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);
    
}
