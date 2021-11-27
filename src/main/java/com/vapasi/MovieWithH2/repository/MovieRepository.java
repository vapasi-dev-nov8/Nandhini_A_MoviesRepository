package com.vapasi.MovieWithH2.repository;

import com.vapasi.MovieWithH2.entity.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends CrudRepository<MovieEntity,String> {
    @Query("SELECT movie FROM MovieEntity movie WHERE movie.actorName IN (:actors)")
    List<MovieEntity> findMoviesByActors(@Param("actors") List<String> actors);

}
