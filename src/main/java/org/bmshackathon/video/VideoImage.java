package org.bmshackathon.video;

/**
 * Created by lk on 21.09.15.
 */
public class VideoImage {
    private static final String DEFAULT_URL = "https://pbs.twimg.com/profile_images/378800000822867536/3f5a00acf72df93528b6bb7cd0a4fd0c.jpeg";
    Long uuid;

    String imageUrl;

    public VideoImage(Long uuid, String imageUrl) {
        this.uuid = uuid;
        this.imageUrl = imageUrl;
    }

    public static VideoImage createDefaultFor(Long uuid) {
        return new VideoImage(uuid, DEFAULT_URL);
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
