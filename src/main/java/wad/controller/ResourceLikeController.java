package wad.controller;

import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wad.service.LikeService;

@RestController
@RequestMapping("/likes")
public class ResourceLikeController {

    @Autowired
    private LikeService likeService;

    @RequestMapping(method = RequestMethod.POST)
    public Long addLike(@RequestParam String resourceId) {
        likeService.addLike(resourceId);
        return likeService.countLikes(resourceId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Integer> getLikeCounts(@RequestParam String[] ids) {
        return likeService.getLikeCounts(Arrays.asList(ids));
    }
}
