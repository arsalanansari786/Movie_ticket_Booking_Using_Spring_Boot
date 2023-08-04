package com.gfg.shoutreview.repository;

import com.gfg.shoutreview.domain.Movie;
import com.gfg.shoutreview.domain.Show;
import com.gfg.shoutreview.resource.ShowResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

//    public Show addShow(Movie movie);
    @Query(value="select * from shows s,theatres t, movies m where m.id=s.movie_id and s.theatre_id=t.id and m.title=? and city=?",nativeQuery = true)
        List<Show> findByMovieNameAndCity(String movieName, String cityName);
    @Query(value =" select * from shows s, theatres t t where s.theater_id=t.id and t.name=? and t.city=?",nativeQuery = true)
    List<Show> findByTheatreNameAndCity(String movieName, String cityName);

    @Query(value= "select * from show s, theatres t where s.theatre_id=t.id and t.city=?",nativeQuery = true)
    List<Show> findByCity(String cityName);
}
