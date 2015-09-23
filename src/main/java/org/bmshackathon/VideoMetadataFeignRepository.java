package org.bmshackathon;

import org.bmshackathon.video.VideoMetadata;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("metadata-store")
@Component
public interface VideoMetadataFeignRepository {

    @RequestMapping(value = "/videoMetadatas", method = RequestMethod.GET)
    List<VideoMetadata> findAll();

    @RequestMapping(value = "/videoMetadatas/{id}", method = RequestMethod.GET)
    VideoMetadata findOne(@PathVariable("id") Long id);
}
