package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "users")
public class GoNowUserController {
    
    @Autowired
    private GoNowUserRepository userRepo;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", userRepo.findAll());
        return "";
    }
    
}
