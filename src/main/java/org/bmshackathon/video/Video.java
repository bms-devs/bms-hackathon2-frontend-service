package org.bmshackathon.video;

/**
 * Created by lk on 21.09.15.
 */
public class Video {
    long id;
    VideoMetadata metadata;
    VideoImage image;

    public Video(long id, VideoMetadata metadata, VideoImage image) {
        this.id = id;
        this.metadata = metadata;
        this.image = image;
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
