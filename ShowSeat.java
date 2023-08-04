package com.gfg.shoutreview.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.shoutreview.Enumeration.SeatType;
import com.gfg.shoutreview.resource.ShowSeatsResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="show_seats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",updatable = false, nullable = false)
    private  long id;

    @Column(name="price")
    private int price;

    @Column(name="SeatNo")
    private String SeatNo;

    @ManyToOne
    @JsonIgnore
    private Show show;

    @Enumerated(EnumType.STRING)
    @Column(name="Seat_Type")
    private SeatType SeatType;

    @ManyToOne
    @JsonIgnore
    private Ticket ticket;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Booked_at")
    @CreationTimestamp
    private Date bookedAt;

    @Column(name="book_Status", columnDefinition = "bit(1)default 0", nullable = false)
    private boolean isBooked;

    public static List<ShowSeatsResource> toResource(List<ShowSeat> seats) {

        if (!CollectionUtils.isEmpty(seats)) {
            return seats.stream().map(ShowSeat::toResource).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public static ShowSeatsResource toResource(ShowSeat seatsEntity) {

        return ShowSeatsResource.builder()
                .id(seatsEntity.getId())
                .SeatNumber(seatsEntity.getSeatNo())
                .rate(seatsEntity.getPrice())
                .seatType(seatsEntity.getSeatType())
                .booked(seatsEntity.isBooked())
                .bookedAt(seatsEntity.getBookedAt())
                .build();

    }

}
