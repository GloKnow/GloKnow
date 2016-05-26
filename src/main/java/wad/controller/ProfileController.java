
package wad.controller;

/**
 *
 * @author Jami V
 */

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.FileObject;
import wad.domain.Person;
import wad.repository.FileObjectRepository;
import wad.repository.PersonRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private FileObjectRepository fileRepository;
    
    @Autowired
    private PersonService personService;
    
    /*@RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable Long personId, Model model) {
        model.addAttribute("profile", personRepository.findOne(personId));
        return "profile";
    }*/
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String viewProfile(@PathVariable String username, Model model) {
        
        Person currUser = personService.getAuthenticatedPerson();
        if (currUser != null) {
            model.addAttribute("username", currUser.getUsername());
        } else {
            model.addAttribute("username", "Login");
        }
        
        model.addAttribute("profile", personRepository.findByUsername(username));
        return "profile";
    }
    
    @RequestMapping(value = "/{personId}/images", method = RequestMethod.POST)
    public void changeProfileImage(@PathVariable Long personId, 
            @RequestParam("file") MultipartFile file) throws IOException {
        Person person = personRepository.findOne(personId);
        
        if (person == null) {
            return;
        }
        
        FileObject fo = new FileObject();
        
        fo.setName(file.getName());
        fo.setMediaType(file.getContentType());
        fo.setSize(file.getSize());
        fo.setContent(file.getBytes());
        
        fileRepository.save(fo);
        
        person.setImageId(fo.getId());
        personRepository.save(person);
    }
    
}
