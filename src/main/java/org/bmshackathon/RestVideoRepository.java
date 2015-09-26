package org.bmshackathon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bmshackathon.client.*;
import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoImage;
import org.bmshackathon.video.VideoMetadata;
import org.bmshackathon.video.VideoReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableCircuitBreaker
public class RestVideoRepository implements VideoRepository {

    private final VideoMetadataFeignClient videoMetadataFeignClient;
    private final VideoImageClient videoImageClient;
    private final VideoReviewClient reviewClient;
    private final VideoPriceCalculatorClient videoPriceCalculatorClient;
    private final RecommendationFeignClient recommendationFeignClient;

    @Autowired
    public RestVideoRepository(VideoMetadataFeignClient videoMetadataFeignClient, VideoImageClient videoImageClient, VideoReviewClient reviewClient, VideoPriceCalculatorClient videoPriceCalculatorClient, RecommendationFeignClient recommendationFeignClient) {
        this.videoMetadataFeignClient = videoMetadataFeignClient;
        this.videoImageClient = videoImageClient;
        this.reviewClient = reviewClient;
        this.videoPriceCalculatorClient = videoPriceCalculatorClient;
        this.recommendationFeignClient = recommendationFeignClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "findDefaultAll")
    public List<Video> findAll() {
        return videoMetadataFeignClient.findAll().stream()
                .map(vm -> Video.withoutPrice(vm.getId(), vm, videoImageClient.findOne(vm.getId())))
                .collect(Collectors.toList());
    }

    public List<Video> findDefaultAll() {
        return new ArrayList<Video>();

    }

    @Override
    @HystrixCommand(fallbackMethod = "findDefaultOne")
    public Video findOne(Long id) {
        VideoMetadata metadata = videoMetadataFeignClient.findOne(id);
        VideoImage videoImage = videoImageClient.findOne(id);
        BigDecimal price = videoPriceCalculatorClient.calculateFor(id);
        return Video.withPrice(id, metadata, videoImage, price);
    }

    public Video findDefaultOne(Long id) {
        VideoMetadata metadata = VideoMetadata.createDefault(id);
        VideoImage videoImage = videoImageClient.findOne(id);
        BigDecimal price = videoPriceCalculatorClient.calculateFor(id);
        return Video.withPrice(id, metadata, videoImage, price);
    }

    @Override
    @HystrixCommand(fallbackMethod = "findAllReviewsDefault")
    public List<VideoReview> findAllReviews(Long id) {
        return reviewClient.findByVideoId(id);
    }

    public List<VideoReview> findAllReviewsDefault(Long id) {
        return new ArrayList<VideoReview>();

    }

    @Override
    @HystrixCommand(fallbackMethod = "getRecommendationsDefault")
    public List<Video> getRecommendation() {
//      List<Long> recommendationIds = recommendationFeignClient.getRecommendation();
        List<Long> recommendationIds = Arrays.asList(new Long(1),new Long(1),new Long(1));
        return recommendationIds.stream()
                .map(id -> Video.withoutPrice(id, videoMetadataFeignClient.findOne(id), videoImageClient.findOne(id)))
                .collect(Collectors.toList());
    }

    public List<Video> getRecommendationsDefault() {
        return new ArrayList<Video>();

    }
}
