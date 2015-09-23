package org.bmshackathon;


import org.bmshackathon.video.Video;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface VideoRepository {
    List<Video> findAll();
    Video findByUuid(Long uuid);
}
