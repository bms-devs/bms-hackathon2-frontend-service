package org.bmshackathon.video;

import javax.persistence.Id;

/**
 * Created by lk on 21.09.15.
 */
public class VideoMetadata {
    @Id
    private Long id;
    private String title;
    private String description;

    VideoMetadata() {
    }

    public VideoMetadata(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public static VideoMetadata createDefault(Long id) {
        return new VideoMetadata(id, "default", "no description");
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
