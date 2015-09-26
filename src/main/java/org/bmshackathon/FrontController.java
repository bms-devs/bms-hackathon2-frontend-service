package org.bmshackathon;

import org.apache.commons.io.IOUtils;
import org.bmshackathon.client.VideoImageRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
class FrontController {
    private VideoRepository repository;
    @Autowired private VideoImageRestTemplateClient videoImageRestTemplateClient;
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

    @RequestMapping(value = "/getImageAscii/{id}", method = GET)
    @ResponseBody
    public void getImageAscii(@PathVariable Long id, HttpServletResponse response) throws IOException {
        byte[] s = videoImageRestTemplateClient.findAscii(id);



        //System.out.println("getImageAscii " + new String(s));
//        return new String(s);

        IOUtils.copy(new ByteArrayInputStream(s), response.getOutputStream());
    }
}
