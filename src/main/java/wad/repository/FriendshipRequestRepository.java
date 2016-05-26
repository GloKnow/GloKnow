package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.FriendshipRequest;
import wad.domain.FriendshipRequest.Status;
import wad.domain.Person;

public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {

    FriendshipRequest findBySourceAndTarget(Person source, Person target);

    List<FriendshipRequest> findBySourceOrTargetAndStatus(Person source, Person target, Status status);

    List<FriendshipRequest> findByTargetAndStatus(Person authenticatedPerson, Status status);

}
