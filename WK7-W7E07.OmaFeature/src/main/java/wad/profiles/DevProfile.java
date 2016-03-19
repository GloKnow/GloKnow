package wad.profiles;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import wad.domain.Person;
import wad.domain.Post;
import wad.repository.PersonRepository;
import wad.repository.PostRepository;

@Configuration
@Profile(value = {"dev", "default"})
public class DevProfile {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    public void init() {
        Person jackB = new Person();
        jackB.setName("Jack Bauer");
        jackB.setSlogan("I'm federal agent Jack Bauer. This is the longest day of my life.");
        jackB.setUsername("jackb");
        jackB.setPassword("jackb");

        jackB = personRepository.save(jackB);

        Person jackR = new Person();
        jackR.setName("Jack Reacher");
        jackR.setSlogan("I know I'm smarter than an armadillo.");
        jackR.setUsername("jackr");
        jackR.setPassword("jackr");
        jackR = personRepository.save(jackR);
        
        
        Post post = new Post();
        post.setContent("Now they broke my toothbrush, I don't own anything.");
        post.setAuthor(jackR);
        
        postRepository.save(post);
        
        Post post2 = new Post();
        post2.setContent(":(");
        post2.setAuthor(jackB);
        
        postRepository.save(post2);
        
        Post post3 = new Post();
        post3.setContent("I'm not a vagrant. I'm a hobo. Big difference.");
        post3.setAuthor(jackR);
        
        postRepository.save(post3);
    }
}
