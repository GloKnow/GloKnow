package hello;

import java.util.List;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoNowUserRepository extends JpaRepository<GoNowUser, Long> {

    List<GoNowUser> findByLastName(String lastName);
}
