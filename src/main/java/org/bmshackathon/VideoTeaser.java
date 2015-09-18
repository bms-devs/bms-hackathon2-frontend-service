package org.bmshackathon;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class VideoTeaser {
    @Id
    private Long id;
    private String title;
    private String imageUrl;
    private String description;

    //Hibernate need this shit
    VideoTeaser() {
    }

    public VideoTeaser(Long id, String title, String imageUrl, String description) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "VideoTeaser{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}