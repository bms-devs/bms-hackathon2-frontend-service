package org.bmshackathon.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;

@EnableCircuitBreaker
@Component
public class VideoPriceCalculatorClient {

    @HystrixCommand(fallbackMethod = "defaultPrice")
    public BigDecimal calculateFor(Long id) {
        throw new NotImplementedException();
    }

    public BigDecimal defaultPrice(Long id) {
        // It's free!
        return BigDecimal.valueOf(0.0);
    }
}
