package wad.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Person;
import wad.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like findByResourceIdAndPerson(String id, Person person);
    Collection<Like> findByResourceIdIn(Collection<String> resourceIds);
    Long countByResourceId(String resourceId);
}
