package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Person;
import wad.domain.Post;
import wad.repository.PostRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private PostRepository postRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Post post) {
        Person person = personService.getAuthenticatedPerson();
        post.setAuthor(person);
        post = postRepository.save(post);
        person.getPosts().add(post);
        
        return "redirect:/index";
    }
}
