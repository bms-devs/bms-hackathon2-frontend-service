package org.bmshackathon;

import org.bmshackathon.client.VideoPriceCalculatorClient;
import org.bmshackathon.client.VideoReviewClient;
import org.bmshackathon.client.VideoImageClient;
import org.bmshackathon.client.VideoMetadataFeignClient;
import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoImage;
import org.bmshackathon.video.VideoMetadata;
import org.bmshackathon.video.VideoReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestVideoRepository implements VideoRepository {

    private final VideoMetadataFeignClient videoMetadataFeignClient;
    private final VideoImageClient videoImageClient;
    private final VideoReviewClient reviewClient;
    private final VideoPriceCalculatorClient videoPriceCalculatorClient;

    @Autowired
    public RestVideoRepository(VideoMetadataFeignClient videoMetadataFeignClient, VideoImageClient videoImageClient, VideoReviewClient reviewClient, VideoPriceCalculatorClient videoPriceCalculatorClient) {
        this.videoMetadataFeignClient = videoMetadataFeignClient;
        this.videoImageClient = videoImageClient;
        this.reviewClient = reviewClient;
        this.videoPriceCalculatorClient = videoPriceCalculatorClient;
    }

    @Override
    public List<Video> findAll() {
        return videoMetadataFeignClient.findAll().stream()
                .map(vm -> Video.withoutPrice(vm.getId(), vm, videoImageClient.findOne(vm.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Video findOne(Long id) {
        VideoMetadata metadata = videoMetadataFeignClient.findOne(id);
        VideoImage videoImage = videoImageClient.findOne(id);
        BigDecimal price = videoPriceCalculatorClient.calculateFor(id);
        return Video.withPrice(id, metadata, videoImage, price);
    }

    @Override
    public List<VideoReview> findAllReviews(Long id) {
        return reviewClient.findAll(id);
    }
}
