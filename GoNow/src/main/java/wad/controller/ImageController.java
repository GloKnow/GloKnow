package wad.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wad.repository.FileObjectRepository;

@Controller
@RequestMapping("/images")
public class ImageController {
    
    @Autowired
    FileObjectRepository fileObjectRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public String saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Look here!");
        System.out.println(file.getName());
        System.out.println(file.getBytes().length);
        return "redirect:/activities";
    }
}
