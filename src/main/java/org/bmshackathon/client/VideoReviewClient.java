package org.bmshackathon.client;

import org.bmshackathon.video.VideoReview;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("review-service")
public interface VideoReviewClient {

    @RequestMapping(value = "/findByVideoId/{id}", method = RequestMethod.GET)
    public List<VideoReview> findByVideoId(@PathVariable("id") Long id);

}
