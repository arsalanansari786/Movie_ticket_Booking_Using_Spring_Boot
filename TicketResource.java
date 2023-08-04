package com.gfg.shoutreview.resource;

import com.gfg.shoutreview.domain.Show;
import com.gfg.shoutreview.domain.ShowSeat;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class TicketResource {
    private long id;

    private String allottedSeats;

    private double amount;

    private Date bookedAt;

    private ShowResource show;
}
