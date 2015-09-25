package org.bmshackathon;

import org.bmshackathon.client.VideoReviewClient;
import org.bmshackathon.client.VideoImageClient;
import org.bmshackathon.client.VideoMetadataFeignClient;
import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoImage;
import org.bmshackathon.video.VideoMetadata;
import org.bmshackathon.video.VideoReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestVideoRepository implements VideoRepository {

    private final VideoMetadataFeignClient videoMetadataFeignClient;
    private final VideoImageClient videoImageClient;
    private final VideoReviewClient reviewClient;

    @Autowired
    public RestVideoRepository(VideoMetadataFeignClient videoMetadataFeignClient, VideoImageClient videoImageClient, VideoReviewClient reviewClient) {
        this.videoMetadataFeignClient = videoMetadataFeignClient;
        this.videoImageClient = videoImageClient;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Video> findAll() {
        return videoMetadataFeignClient.findAll().stream()
                .map(vm -> new Video(vm.getId(), vm, videoImageClient.findOne(vm.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Video findOne(Long id) {
        VideoMetadata metadata = videoMetadataFeignClient.findOne(id);
        VideoImage videoImage = videoImageClient.findOne(id);
        return new Video(id, metadata, videoImage);
    }

    @Override
    public List<VideoReview> findAllReviews(Long id) {
        return reviewClient.findAll(id);
    }
}
