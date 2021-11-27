package com.vapasi.MovieWithH2.controller;

import com.vapasi.MovieWithH2.dto.MovieDto;
import com.vapasi.MovieWithH2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/movie")
public class MoviesController {

    MovieService movieService;

    @Autowired
    public MoviesController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> allMovieList = movieService.getAllMovies();
        return ResponseEntity.ok().body(allMovieList);
    }

    @PostMapping("/")
    public ResponseEntity saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto createdMovieDto = movieService.saveMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovieDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieForId(@PathVariable String id) {
        Optional<MovieDto> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok().body(movie.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovieDetails(@PathVariable String id, @RequestBody MovieDto movieDto) {
        MovieDto updatedMovieDetails = movieService.updateMovieDetails(id, movieDto);
        return ResponseEntity.ok().body(updatedMovieDetails);
    }
    @GetMapping("/actorsList")
    public ResponseEntity<List<MovieDto>> getMoviesOfActors(@RequestParam(value="actors") List<String> actors){
        List<MovieDto> retreivedMovieDetails = movieService.findByActors(actors);
        return ResponseEntity.ok().body(retreivedMovieDetails);
    }
}