package org.bmshackathon.client;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class VideoPriceCalculatorClient {

    //TODO
    public BigDecimal calculateFor(Long id) {
        return BigDecimal.valueOf(999999999.01);
    }
}
