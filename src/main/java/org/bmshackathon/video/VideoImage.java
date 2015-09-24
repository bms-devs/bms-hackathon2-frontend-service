package org.bmshackathon.video;

/**
 * Created by lk on 21.09.15.
 */
public class VideoImage {
    private static final String DEFAULT_URL = "https://pbs.twimg.com/profile_images/378800000822867536/3f5a00acf72df93528b6bb7cd0a4fd0c.jpeg";
    Long id;
    String imageUrl;

    public VideoImage() {
    }

    public VideoImage(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public static VideoImage createDefaultFor(Long id) {
        return new VideoImage(id, DEFAULT_URL);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
