package org.bmshackathon;


import org.bmshackathon.video.Video;
import org.bmshackathon.video.VideoReview;

import java.util.List;

//@RepositoryRestResource
public interface VideoRepository {
    List<Video> findAll();
    Video findOne(Long id);
    List<VideoReview> findAllReviews(Long id);
}
