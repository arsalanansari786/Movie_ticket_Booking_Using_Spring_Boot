package com.gfg.shoutreview.resource;


import com.gfg.shoutreview.Enumeration.Genre;
import com.gfg.shoutreview.domain.Movie;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResource {

//    private String title;
//    private Genre genre;

//    public Movie toMovie(){
//        return Movie.builder().title(title).genre(genre).rating(0.0).build();
//    }

    private long id;

    private String title;

    private Genre genre;

    private List<ReviewResource> reviews;

}
