package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Activity;
import wad.domain.Person;
import wad.domain.Post;
import wad.repository.ActivityRepository;
import wad.repository.PostRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ActivityRepository activityRepository;
    
    @RequestMapping(value = "/{activityId}", method = RequestMethod.POST)
    public String create(@ModelAttribute Post post, @PathVariable String activityId) {
        Person person = personService.getAuthenticatedPerson();
        post.setAuthor(person);
        
        Activity activity = activityRepository.findOne(activityId);
        post.setActivity(activity);
        
        post = postRepository.save(post);
        person.getPosts().add(post);
        
        return "redirect:/activities/display/{activityId}";
    }
}
