package org.bmshackathon.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bmshackathon.video.VideoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
                .map(service -> new RestTemplate().getForObject(service.getUri() + "/videoImage/" + id, VideoImage.class))
                .orElseGet(() -> VideoImage.createDefaultFor(id));
    }
    public byte[] findAscii(Long id) {
        //let's assume for a moment that image store is not eligible for use for a feign client
        return anyServiceInstance("imageurl-store")
                .map(service -> new RestTemplate().exchange(service.getUri() + "/ascii/" + id, HttpMethod.GET,
                        new HttpEntity<String>(new HttpHeaders()), byte[].class).getBody())
                .orElseGet(() -> new byte[0]);
    }



    private Optional<ServiceInstance> anyServiceInstance(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if(instances.size() == 0) {
            return Optional.empty();
        }else {
            //yes, you have found a [professional - lk] load balancer.
            return Optional.of(instances.get(new Random().nextInt(instances.size())));
        }
    }
}
