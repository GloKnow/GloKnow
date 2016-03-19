package wad.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Like;
import wad.domain.Person;
import wad.repository.LikeRepository;

@Service
public class LikeService {

    @Autowired
    private LikeRepository resourceLikeRepository;

    @Autowired
    private PersonService personService;

    public Like addLike(String resourceId) {
        Person p = personService.getAuthenticatedPerson();

        if (resourceLikeRepository.findByResourceIdAndPerson(resourceId, p) != null) {
            return null;
        }

        Like like = new Like();
        like.setResourceId(resourceId);
        like.setPerson(p);
        return resourceLikeRepository.save(like);
    }

    public Map<String, Integer> getLikeCounts(Collection<String> resourceIds) {
        Map<String, Integer> likeCounts = new HashMap<>();

        for (Like like : resourceLikeRepository.findByResourceIdIn(resourceIds)) {
            if (!likeCounts.containsKey(like.getResourceId())) {
                likeCounts.put(like.getResourceId(), 0);
            }

            likeCounts.put(like.getResourceId(), likeCounts.get(like.getResourceId()) + 1);
        }

        return likeCounts;
    }

    public Long countLikes(String resourceId) {
        return resourceLikeRepository.countByResourceId(resourceId);
    }
}
