package com.gfg.shoutreview.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gfg.shoutreview.Enumeration.SeatType;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="theatre_seats")
@Builder
@Getter
@Setter
public class TheatreSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name="seat_type")
    private SeatType seat_type;

    @Column(name="seat_number")
    private String seat_number;

    @ManyToOne
    @JsonIgnore
    private Theatre theatre;

}
