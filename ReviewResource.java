package com.gfg.shoutreview.resource;


import com.gfg.shoutreview.domain.Movie;
import com.gfg.shoutreview.domain.Review;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ReviewResource {

    private String movieReview;

    private double rating;

    private Long movieId;

//    private static Review toReview(ReviewResource reviewResource){
//       return Review.builder().
//    }
}





























