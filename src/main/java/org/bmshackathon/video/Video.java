package org.bmshackathon.video;

/**
 * Created by lk on 21.09.15.
 */
public class Video {
    long uuid;
    VideoMetadata metadata;
    VideoImage image;

    public Video(long uuid, VideoMetadata metadata, VideoImage image) {
        this.uuid = uuid;
        this.metadata = metadata;
        this.image = image;
    }

    public Long getUuid() {
        return uuid;
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

    @Override
    public String toString() {
        return "VideoTeaser{" +
                "id='" + getUuid()+ '\'' +
                ", title='" + getTitle() + '\'' +
                ", imageUrl='" + getImageUrl() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
