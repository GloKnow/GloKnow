package wad.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Person;
import wad.domain.Post;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByAuthorIn(Collection<Person> persons, Pageable pageable);
}
