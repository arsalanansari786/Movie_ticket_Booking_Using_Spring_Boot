package com.gfg.shoutreview.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.shoutreview.resource.TheaterResource;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="theatres")
@Getter
@Setter
@Builder
@Data
public class Theatre {
     @Id
     @GeneratedValue(strategy =GenerationType.IDENTITY)
     private long id;

     @Column(name = "name", nullable = false)
     private String name;
     @Column(name = "city", nullable = false)
     private String city;
     @Column(name = "address", nullable = false)
     private String address;
     @OneToMany(mappedBy = "theatre" +
             " ", cascade = CascadeType.ALL)
     @JsonIgnore
     @Builder.Default
     private List<Show> shows = new ArrayList<>();

     @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
     @JsonIgnore
     @Builder.Default
     private List<TheatreSeats> seats = new ArrayList<>();

     public static Theatre toEntity(TheaterResource theaterResource) {
          return Theatre.builder().name(theaterResource.getName())
                  .city(theaterResource.getCity())
                  .address(theaterResource.getAddress())
                  .build();
     }

     public static TheaterResource toResource(Theatre theatre) {
         return TheaterResource.builder().id(theatre.getId())
                  .name(theatre.getName())
                  .city(theatre.getCity())
                  .address(theatre.getAddress())
                  .build();
     }
}
