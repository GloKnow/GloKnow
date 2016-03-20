package wad.controller;

import java.util.List;
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
import wad.repository.ActivityRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Activity> activities = activityRepository.findAll();
        model.addAttribute("activities", activities);
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Activity activity, @RequestParam(required=false) String addme) {
        System.out.println("Turns out the program didn't crash before getting here.");
        Person person = personService.getAuthenticatedPerson();
        activity.setCreator(person);
        if (addme != null) activity.addAttendee(person);
        activityRepository.save(activity);
        person.getOwnedActivities().add(activity);
        return "redirect:/activities";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCreate() {
        return "addevent";
    }
    
    @RequestMapping(value = "/{activityId}", method = RequestMethod.POST)
    public String joinActivity(@PathVariable Long activityId) {
        Person person = personService.getAuthenticatedPerson();
        Activity activity = activityRepository.findOne(activityId);
        person.getAttendedActivities().add(activity);
        activity.getAttendees().add(person);
        return "reditect:/activities";
    }
}
