package org.bmshackathon;


import org.bmshackathon.video.Video;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.management.ServiceNotFoundException;
import java.util.List;

@RepositoryRestResource
public interface VideoRepository {
    List<Video> findAll() throws ServiceNotFoundException;
    List<Video> findByUuid(Long uuid);
}
