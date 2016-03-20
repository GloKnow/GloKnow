package wad.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String create(@ModelAttribute Activity activity) {
        Person person = personService.getAuthenticatedPerson();
        activity.setCreator(person);
        activityRepository.save(activity);
        person.getOwnedActivities().add(activity);
        return "redirect:/activities";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String displayCreate() {
        return "addevent";
    }
}
