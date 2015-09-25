package org.bmshackathon.video;

import java.math.BigDecimal;
import java.util.Optional;


public class Video {
    final long id;
    final VideoMetadata metadata;
    final VideoImage image;
    final Optional<BigDecimal> price;

    private Video(long id, VideoMetadata metadata, VideoImage image, Optional<BigDecimal> price) {
        this.id = id;
        this.metadata = metadata;
        this.image = image;
        this.price = price;
    }

    public static Video withPrice(long id, VideoMetadata metadata, VideoImage image, BigDecimal price) {
        return new Video(id, metadata, image, Optional.of(price));
    }

    public static Video withoutPrice(long id, VideoMetadata metadata, VideoImage image) {
        return new Video(id, metadata, image, Optional.empty());
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return metadata.getTitle();
    }

    public String getImageUrl() {
        return image.imageUrl;
    }

    public String getDescription() {
        return metadata.getDescription();
    }

    public String getPriceAsText() {
        return price
                .map(price -> "$"+price)
                .orElseGet(() -> "");
    }

    @Override
    public String toString() {
        return "VideoTeaser{" +
                "id='" + getId()+ '\'' +
                ", title='" + getTitle() + '\'' +
                ", imageUrl='" + getImageUrl() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
