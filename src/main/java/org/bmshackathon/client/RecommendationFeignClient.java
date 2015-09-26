package org.bmshackathon.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("recommendation-service")
public interface RecommendationFeignClient {

    @RequestMapping(value = "/recommended", method = RequestMethod.GET)
    List<Long> getRecommendation();
}
