package com.gfg.shoutreview.service;

import com.gfg.shoutreview.Enumeration.Genre;
import com.gfg.shoutreview.Exception.NotFoundException;
import com.gfg.shoutreview.domain.Movie;
import com.gfg.shoutreview.repository.MovieRepository;
import com.gfg.shoutreview.resource.MovieResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public MovieResource addMovie(MovieResource movieRequest) {

        Movie movie = Movie.toEntity(movieRequest);

        if (movieRepository.existsByTitle(movieRequest.getTitle())) {
            return Movie.toResource(movie);
        }

        movie = movieRepository.save(movie);

        log.info("Added New Movie"+ movie.toString());

        return Movie.toResource(movie);
    }

    public MovieResource getMovie(String title) {
        //add id check if valid
        Movie movie = movieRepository.findByTitle(title);

        if (Objects.isNull(movie)) {
            throw new NotFoundException("Movie not found:" + title);
        }

        return Movie.toResource(movie);
    }

    public List<MovieResource> findMoviesByGenre(String genre) {
        if (Arrays.stream(Genre.values()).noneMatch(g -> g.toString().equals(genre)))
            return new ArrayList<>();
        List<Movie> movieList = movieRepository.findByGenre(Genre.valueOf(genre));
        if (!CollectionUtils.isEmpty(movieList)) {
            List<MovieResource> movieResponseList = movieList.stream().sorted(Comparator.comparing(Movie::getRating, Comparator.reverseOrder())).map(m -> m.toMovieResponse()).collect(Collectors.toList());
            if (movieResponseList.size() > 5)
                return movieResponseList.subList(0, 4);
            return movieResponseList;
        }
        return new ArrayList<>();
    }
    public MovieResource getMovie(long id) {
        //add id check if valid
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()) {
            throw new NotFoundException("Movie not found:" + id);
        }

        return Movie.toResource(movie.get());
    }



}
