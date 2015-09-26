package org.bmshackathon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
class FrontController {
    private VideoRepository repository;

    @Autowired
    FrontController(VideoRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value="/", method = GET)
    public String index(Map<String, Object> model) {
        model.put("videos", repository.findAll());
        model.put("recommendations", repository.getRecommendation());
        return "index";
    }

    @RequestMapping(value = "/video/{id}/details", method = GET)
    public String videoDetail(@PathVariable Long id, Map<String, Object> model) {
        model.put("video", repository.findOne(id));
        model.put("reviews", repository.findAllReviews(id));
        return "details";
    }
}
