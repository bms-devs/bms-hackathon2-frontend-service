package org.bmshackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
class MainController {
    private VideoTeaserRepository repository;

    @Autowired
    MainController(VideoTeaserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/", method = GET)
    public String index(Map<String, Object> model) {
        model.put("videos", repository.findAll());
        return "index";
    }

    @RequestMapping(value = "/video/{id}/details", method = GET)
    public String videoDetail(@PathVariable Long id,  Map<String, Object> model) {
        VideoTeaser maybeVideoTeaser = repository.findOne(id);
        if(maybeVideoTeaser == null) {
            throw new IllegalArgumentException("Video with id="+id+" does not exist");
        }
        model.put("video", maybeVideoTeaser);
        return "details";
    }
}
