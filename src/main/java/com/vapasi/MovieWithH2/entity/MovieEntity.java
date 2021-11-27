package com.vapasi.MovieWithH2.entity;

import com.vapasi.MovieWithH2.dto.MovieDto;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String actorName;
    private String directorName;

    public MovieEntity(String id, String name, String actorName, String directorName) {
        this.id = id;
        this.name = name;
        this.actorName = actorName;
        this.directorName = directorName;
    }

    public static MovieEntity entityFrom(MovieDto movieDto) {
        return new MovieEntity(null, movieDto.getName(), movieDto.getActorName(), movieDto.getDirectorName());
    }

    public MovieDto movieDto() {
        return new MovieDto(this.id, this.name, this.actorName, this.directorName);
    }

    public MovieEntity() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActorName() {
        return actorName;
    }

    public String getDirectorName() {
        return directorName;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", actorName='" + actorName + '\'' +
                ", directorName='" + directorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieEntity)) return false;
        MovieEntity movies = (MovieEntity) o;
        return Objects.equals(name, movies.name) && Objects.equals(actorName, movies.actorName) && Objects.equals(directorName, movies.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, actorName, directorName);
    }
}
