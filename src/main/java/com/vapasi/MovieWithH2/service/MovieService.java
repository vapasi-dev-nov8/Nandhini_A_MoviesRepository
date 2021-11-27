package com.vapasi.MovieWithH2.service;

import com.vapasi.MovieWithH2.dto.MovieDto;
import com.vapasi.MovieWithH2.entity.MovieEntity;
import com.vapasi.MovieWithH2.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }
    public List<MovieDto> getAllMovies() {
    List<MovieDto> movieList = new ArrayList<MovieDto>();

    for(MovieEntity movie:movieRepository.findAll()){
       movieList.add(MovieDto.dtoFrom(movie));
    }
    return movieList;
    }

    public MovieDto saveMovie(MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        MovieEntity savedMovieEntity = movieRepository.save(movieEntity);
        MovieDto savedMovieDto = MovieDto.dtoFrom(savedMovieEntity);
        return savedMovieDto;
    }

    public Optional<MovieDto> getMovieById(String id) {
        Optional<MovieEntity> foundMovie = movieRepository.findById(id);
        return foundMovie.map(MovieDto::dtoFrom);

    }

    public MovieDto updateMovieDetails(String id, MovieDto movieDto) {
        MovieEntity movieEntity = MovieEntity.entityFrom(movieDto);
        Optional<MovieEntity> movieDetailsForId = movieRepository.findById(id);
        MovieEntity movieDetailsToBeUpdated = new MovieEntity(id,movieEntity.getName(),movieEntity.getActorName(),movieEntity.getDirectorName());
        MovieEntity updatedMovieDetail = movieRepository.save(movieDetailsToBeUpdated);
        return MovieDto.dtoFrom(updatedMovieDetail);
    }

    public List<MovieDto> findByActors(List<String> actorsList) {
        List<MovieEntity> moviesOfActors = movieRepository.findMoviesByActors(actorsList);
        List<MovieDto> moviesDtoList = new ArrayList<MovieDto>();
        for(MovieEntity movie: moviesOfActors){
            moviesDtoList.add(MovieDto.dtoFrom(movie));
        }
        return moviesDtoList;
    }
}

