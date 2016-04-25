package wad.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Activity;
import wad.domain.Person;
import wad.domain.Post;
import wad.repository.ActivityRepository;
import wad.repository.PostRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ActivityRepository activityRepository;
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        
        List<Activity> activities = activityRepository.findAll();
        model.addAttribute("activities", activities);
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Activity activity, @RequestParam(required=false) String addme) {
        Person person = personService.getAuthenticatedPerson();
        activity.setCreator(person);
        if (addme != null) activity.addAttendee(person);
        activityRepository.save(activity);
        person.getOwnedActivities().add(activity);
        return "redirect:/activities";
    }
    
    @RequestMapping(value = "/remove/{activityId}", method = RequestMethod.POST)
    public String remove(@PathVariable String activityId)
    {
        Activity activity = activityRepository.findOne(activityId);
        Person currUser = personService.getAuthenticatedPerson();
        Person creator = activity.getCreator();
        if (currUser == creator) {
            List<Person> attendeelist = activity.getAttendees();
            while(!attendeelist.isEmpty())
            {
                Person attendee = attendeelist.get(0);
                this.leave(attendee,activity);
            }
            activityRepository.delete(activity);
        }
        return "redirect:/activities";
        
    }
    
    private void leave(Person person, Activity activity)
    {
        List<Person> attendeelist = activity.getAttendees();
        if (attendeelist.contains(person)) {
            person.getAttendedActivities().remove(activity);
            activity.getAttendees().remove(person);
        }
        
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCreate() {
        return "addevent";
    }
    
    @Transactional
    @RequestMapping(value = "/{activityId}", method = RequestMethod.POST)
    public String joinActivity(@PathVariable String activityId) {
        Person person = personService.getAuthenticatedPerson();
        Activity activity = activityRepository.findOne(activityId);
        List<Person> attendeelist = activity.getAttendees();
        if (!attendeelist.contains(person)) {
            person.getAttendedActivities().add(activity);
            activity.getAttendees().add(person);
        }
        return "redirect:/activities";
    }
    
    @RequestMapping(value = "/{activityId}", method = RequestMethod.GET)
    public String showActivity(@PathVariable String activityId, Model model) {
        Activity activity = activityRepository.findOne(activityId);
        model.addAttribute("activity", activity);
        return "activity";
    }

    /**
     * @param personService the personService to set
     * For Mockito testing. Cannot seem to inject a mock personService otherwise.
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
