package org.bmshackathon.video;

import java.math.BigDecimal;

public class VideoPrice {
    private Long id;
    private BigDecimal price;

    public VideoPrice() {
    }

    public VideoPrice(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
