package org.bmshackathon;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VideoTeaserRepository extends CrudRepository<VideoTeaser, Long>{
}
