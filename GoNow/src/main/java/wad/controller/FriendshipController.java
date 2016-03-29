package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.FriendshipRequest;
import wad.domain.Person;
import wad.repository.FriendshipRequestRepository;
import wad.repository.PersonRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/friends")
public class FriendshipController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private FriendshipRequestRepository friendshipRequestRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String requestFriendship(@RequestParam Long personId) {
        Person source = personService.getAuthenticatedPerson();
        Person target = personRepository.findOne(personId);

        if (friendshipRequestRepository.findBySourceAndTarget(source, target) != null) {
            // if we have already made a friend request, let's not do another
            return "redirect:/index";
        }

        // check if there is an existing friend requests, if so, accept it
        FriendshipRequest existing = friendshipRequestRepository.findBySourceAndTarget(target, source);
        if (existing != null) {
            existing.setStatus(FriendshipRequest.Status.ACCEPTED);
            friendshipRequestRepository.save(existing);

            return "redirect:/index";
        }

        FriendshipRequest request = new FriendshipRequest();
        request.setSource(source);
        request.setTarget(target);

        friendshipRequestRepository.save(request);

        return "redirect:/index";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {

        List<FriendshipRequest> requests = friendshipRequestRepository.findByTargetAndStatus(personService.getAuthenticatedPerson(), FriendshipRequest.Status.REQUESTED);
        if (requests != null && requests.size() > 0) {
            model.addAttribute("friendshipRequests", requests);
        }

        return "friends";
    }
}
