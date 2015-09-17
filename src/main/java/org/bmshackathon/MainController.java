package org.bmshackathon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
class MainController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("time", new Date());

        model.put("videos", new VideoRepository().findAll());

        return "index";
    }
}
