package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Activity;
import wad.repository.ActivityRepository;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository activityRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Activity activity) {
        activityRepository.save(activity);
        return "redirect:/index";
    }
}
