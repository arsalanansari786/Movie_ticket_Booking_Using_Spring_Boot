package com.gfg.shoutreview.resource;

import com.gfg.shoutreview.Enumeration.SeatType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeatsResource {
    private long id;
    private int rate;
    private String SeatNumber;
    private SeatType seatType;

    private boolean booked;

    private Date bookedAt;


}
