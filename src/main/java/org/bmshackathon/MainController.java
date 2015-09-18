package org.bmshackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
class MainController {
    private VideoTeaserRepository repository;

    @Autowired
    MainController(VideoTeaserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("videos", repository.findAll());
        return "index";
    }
}
