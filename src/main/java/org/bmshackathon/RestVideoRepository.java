package org.bmshackathon;

import org.bmshackathon.client.VideoImageClient;
import org.bmshackathon.client.VideoMetadataFeignClient;
import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoImage;
import org.bmshackathon.video.VideoMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestVideoRepository implements VideoRepository {

    private VideoMetadataFeignClient videoMetadataFeignClient;
    private VideoImageClient videoImageClient;

    @Autowired
    public RestVideoRepository(VideoMetadataFeignClient videoMetadataFeignClient, VideoImageClient videoImageClient) {
        this.videoMetadataFeignClient = videoMetadataFeignClient;
        this.videoImageClient = videoImageClient;
    }

    @Override
    public List<Video> findAll() {
        return videoMetadataFeignClient.findAll().stream()
                .map(vm -> new Video(vm.getUuid(), vm, videoImageClient.findOne(vm.getUuid())))
                .collect(Collectors.toList());
    }

    @Override
    public Video findOne(Long id) {
        VideoMetadata metadata = videoMetadataFeignClient.findOne(id);
        VideoImage videoImage = videoImageClient.findOne(id);
        return new Video(id, metadata, videoImage);
    }
}
