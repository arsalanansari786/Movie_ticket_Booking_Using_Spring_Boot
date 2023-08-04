package com.gfg.shoutreview.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.shoutreview.Enumeration.Genre;
import com.gfg.shoutreview.resource.MovieResource;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movies")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class Movie implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Double rating;// a single entity which is average rating of all reviews for a movie

    @OneToMany(mappedBy="movie")
    private List<Review> reviews;

    @OneToMany(mappedBy = "movie" ,cascade = CascadeType.ALL)
    @JsonIgnore
    @Builder.Default
    private List<Show> shows=new ArrayList<>();

    public static Movie toEntity(MovieResource movieRequest) {

        return Movie.builder()
                .title(movieRequest.getTitle())
                .genre(movieRequest.getGenre())
                .build();

    }

    public static MovieResource toResource(Movie movie) {

        return MovieResource.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .reviews(Review.toResource(movie.getReviews()))
                .build();
    }



}
