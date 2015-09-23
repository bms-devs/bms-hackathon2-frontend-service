package org.bmshackathon;

import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class VideoRepositoryRESTImpl implements VideoRepository {

    private DiscoveryClient discoveryClient;
    private VideoMetadataFeignRepository videoMetadataFeignRepository;

    @Autowired
    public VideoRepositoryRESTImpl(DiscoveryClient discoveryClient, VideoMetadataFeignRepository videoMetadataFeignRepository) {
        this.discoveryClient = discoveryClient;
        this.videoMetadataFeignRepository = videoMetadataFeignRepository;
    }

    @Override
    public List<Video> findAll() {
        return videoMetadataFeignRepository.findAll().stream()
                .map(vm -> new Video(vm.getUuid(), vm, getImageForUuid(vm.getUuid())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Video> findByUuid(Long uuid) {
        throw new NotImplementedException();
    }

    private VideoImage getImageForUuid(Long uuid) {
        RestTemplate restTemplate = new RestTemplate();

        // let's assume for a moment that image store is not eligible for use for a feign client
        return anyServiceInstance("imageurl-store")
                .map(service -> restTemplate.getForObject(service.getUri() + "/image/" + uuid, VideoImage.class))
                .orElseGet(() -> VideoImage.createDefaultFor(uuid));
    }

    private Optional<ServiceInstance> anyServiceInstance(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if(instances.size() == 0) {
            return Optional.empty();
        }else {
            //yes, you have found a load balancer.
            return Optional.of(instances.get(new Random().nextInt(instances.size())));
        }
    }
}
