package org.bmshackathon;

import java.util.Arrays;
import java.util.List;

class VideoView {
    final String id;
    final String title;
    final String imageUrl;
    final String description;

    public VideoView(String id, String title, String imageUrl, String description) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getId() {
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
        return "VideoView{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


class VideoRepository {
    private final List<VideoView> videos;

    public VideoRepository() {
        videos = Arrays.asList(
                new VideoView("1", "Card Title", "http://brandflow.pl/wp-content/uploads/2015/08/k2.jpg", "Lorem ipsum dolor sit amet enim. Etiam ullamcorper. Suspendisse a pellentesque dui, non felis."),
                new VideoView("2", "Second Video", "http://brandflow.pl/wp-content/uploads/2015/08/k5.jpg", "Lorem ipsum dolor sit amet enim. Etiam ullamcorper. Suspendisse a pellentesque dui, non felis."),
                new VideoView("3", "Godzilla", "http://brandflow.pl/wp-content/uploads/2015/08/k6.jpg", "Lorem ipsum dolor sit amet enim. Etiam ullamcorper. Suspendisse a pellentesque dui, non felis.")
        );
    }

    List<VideoView> findAll() {
        return this.videos;
    }
}