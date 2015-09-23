package org.bmshackathon.client;

import org.bmshackathon.video.VideoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class VideoImageRestTemplateClient implements VideoImageClient{

    private DiscoveryClient discoveryClient;

    @Autowired
    public VideoImageRestTemplateClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public VideoImage findOne(Long id) {
        //let's assume for a moment that image store is not eligible for use for a feign client
        return anyServiceInstance("imageurl-store")
                .map(service -> new RestTemplate().getForObject(service.getUri() + "/image/" + id, VideoImage.class))
                .orElseGet(() -> VideoImage.createDefaultFor(id));
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
