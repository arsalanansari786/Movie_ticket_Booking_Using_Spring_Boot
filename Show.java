package com.gfg.shoutreview.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.gfg.shoutreview.resource.ShowResource;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="shows")
@Builder
@Getter
@Setter
public class Show {
    @Id
    @Column(name="id",updatable = false,nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern ="YYY-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    @JsonSerialize(using=LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name="show_time", columnDefinition="TIME",nullable = false)
    private LocalDateTime showTime;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created_at")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedDate;

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    @JsonIgnore
    private Theatre theatre;


    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeat> seats;
    public static ShowResource toResource(Show show) {

        return ShowResource.builder()
                .id(show.getId())
                .showTime(show.getShowTime())
                .movieId(show.getMovie().getId())
                .theaterId(show.getTheatre().getId())
                .seats(ShowSeat.toResource(show.getSeats()))
                .createdAt(show.getCreatedDate())
                .updatedAt(show.getUpdatedDate())
                .build();


    }
    public static List<ShowResource> toResource(List<Show> show) {

        if (!CollectionUtils.isEmpty(show)) {
            return show.stream().map(Show::toResource).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }
    public static Show toEntity(ShowResource showResource) {

        return Show.builder()
                .showTime(showResource.getShowTime())
                .build();

    }
}
