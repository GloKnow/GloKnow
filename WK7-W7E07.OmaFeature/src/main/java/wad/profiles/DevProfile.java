package wad.profiles;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import wad.domain.Activity;
import wad.domain.Person;
import wad.domain.Post;
import wad.repository.ActivityRepository;
import wad.repository.PersonRepository;
import wad.repository.PostRepository;

@Configuration
@Profile(value = {"dev", "default"})
public class DevProfile {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ActivityRepository activityRepository;

    @PostConstruct
    public void init() {
        Person jackB = new Person();
        jackB.setFirstname("Jack");
        jackB.setSurname("Bauer");
        jackB.setBirthday("Today");
        jackB.setEmail("asdasdasd@asd.asd");
        jackB.setPhone("040-toolazy");
        jackB.setUsername("jackb");
        jackB.setPassword("jackb");

        jackB = personRepository.save(jackB);

        Person jackR = new Person();
        jackR.setFirstname("Jack");
        jackR.setSurname("Reacher");
        jackR.setBirthday("Today");
        jackR.setEmail("asdasdasd@asd.asd");
        jackR.setPhone("040-toolazy");
        jackR.setUsername("jackr");
        jackR.setPassword("jackr");
        jackR = personRepository.save(jackR);
        
        Activity footballAct = new Activity();
        footballAct.setName("Football");
        footballAct.setTime("Tomorrow");
        footballAct.setLocation("Behind you");
        footballAct.setCreator(jackR);
        footballAct.setDescription("We kick around an innocent ball.");
        footballAct = activityRepository.save(footballAct);
        
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
