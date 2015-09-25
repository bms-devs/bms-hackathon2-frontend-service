package org.bmshackathon.video;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VideoReview {
    String text;
    String clientFullName;
    Integer rating;

    VideoReview() {
    }

    public VideoReview(String text, String clientFullName, Integer rating) {
        this.text = text;
        this.clientFullName = clientFullName;
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public Integer getRating() {
        return rating;
    }

    /*
        Really stupid method for our logic-less template engine ;)
        This tricky stream generates a List of n elements, where n == rating.
        Don't do this at home!
     */
    public List<Integer> getRatingAsList() {
        return Stream
                .generate(() -> rating)
                .limit(rating)
                .collect(Collectors.toList());
    }
}
