package org.bmshackathon.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@EnableCircuitBreaker
@Component
public class VideoPriceCalculatorClient {

    private DiscoveryClient discoveryClient;

    @Autowired
    public VideoPriceCalculatorClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @HystrixCommand(fallbackMethod = "defaultPrice")
    public BigDecimal calculateFor(Long id) {
        return anyServiceInstance("pricing-service")
                .map(service -> new RestTemplate().getForObject(service.getUri() + "/videoPrices/" + id, VideoPrice.class))
                .map(videoPrice -> videoPrice.getPrice() )
                .orElseGet(() -> defaultPrice());
    }

    public BigDecimal defaultPrice(Long id) {
        // It's free!
        return BigDecimal.valueOf(0.0);
    }

    public BigDecimal defaultPrice() {
        // It's free!
        return BigDecimal.valueOf(0.0);
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
