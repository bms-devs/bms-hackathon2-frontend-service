package org.bmshackathon;

import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class VideoRepositoryRESTImpl implements VideoRepository {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private VideoMetadataFeignRepository videoMetadataFeignRepository;

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
        VideoImage image;

        // let's assume for a moment that image store is not eligible for use for a feign client
        ServiceInstance imageUrlServiceInstance = null;
        try {
            imageUrlServiceInstance = anyServiceInstance("imageurl-store");
            image = restTemplate.getForObject(imageUrlServiceInstance.getUri() + "/image/" + uuid, VideoImage.class);
        } catch (ServiceNotFoundException e) {
            image = VideoImage.createDefaultFor(uuid);
        }
        return image;
    }

    private ServiceInstance anyServiceInstance(String serviceName) throws ServiceNotFoundException {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if(instances.size() == 0)
            throw new ServiceNotFoundException();
        return instances.get(new Random().nextInt(instances.size()));
    }
}
