package org.bmshackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
class MainController {
    private VideoMetadataFeignRepository repository;

    @Autowired
    MainController(VideoMetadataFeignRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/", method = GET)
    public String index(Map<String, Object> model) {
        model.put("videos", repository.findAll());
        return "index";
    }

    @RequestMapping(value = "/video/{uuid}/details", method = GET)
    public String videoDetail(@PathVariable Long uuid,  Map<String, Object> model) {
        return "details";
    }
}
