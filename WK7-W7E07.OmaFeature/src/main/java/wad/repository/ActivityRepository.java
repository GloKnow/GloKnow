package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Activity findByName(String name);
    
}
