package com.gfg.shoutreview.service;

import com.gfg.shoutreview.Enumeration.SeatType;
import com.gfg.shoutreview.domain.Theatre;
import com.gfg.shoutreview.domain.TheatreSeats;
import com.gfg.shoutreview.repository.TheatreRepository;
import com.gfg.shoutreview.repository.TheatreSeatsRepository;
import com.gfg.shoutreview.resource.TheaterResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private TheatreSeatsRepository theatreSeatsRepository;
    public TheaterResource addTheatre(TheaterResource theaterResource) {
        Theatre theatre = Theatre.toEntity(theaterResource);
        theatre.getSeats().addAll(getTheatreSeats());
        for (TheatreSeats seatsEntity : theatre.getSeats()) {
            seatsEntity.setTheatre(theatre);
        }
       theatre= theatreRepository.save(theatre);
        log.info("Added New User [id: " + theatre.getId() + ", Name: " + theatre.getName() + "]");
        return Theatre.toResource(theatre);
    }

    private List<TheatreSeats> getTheatreSeats() {
            List<TheatreSeats> seats = new ArrayList<>();

        seats.add(getTheaterSeat("1A", SeatType.REGULAR));
        seats.add(getTheaterSeat("1B", SeatType.REGULAR));
        seats.add(getTheaterSeat("1C", SeatType.REGULAR));
        seats.add(getTheaterSeat("1D", SeatType.REGULAR));
        seats.add(getTheaterSeat("1E", SeatType.REGULAR));

        seats.add(getTheaterSeat("2A", SeatType.RECLINER));
        seats.add(getTheaterSeat("2B", SeatType.RECLINER));
        seats.add(getTheaterSeat("2C", SeatType.RECLINER));
        seats.add(getTheaterSeat("2D", SeatType.RECLINER));
        seats.add(getTheaterSeat("2E", SeatType.RECLINER));
            seats = theatreSeatsRepository.saveAll(seats);

            return seats;
        }
        private TheatreSeats getTheaterSeat(String seatNumber, SeatType seatType) {
            return TheatreSeats.builder().seat_number(seatNumber).seat_type(seatType).build();
        }
        private TheaterResource getTheatre(long id){
            log.info("Searching Theater by id: " + id);
           Optional<Theatre> theaterEntity= theatreRepository.findById(id);
            if (theaterEntity.isEmpty()) {
                log.error("Theater not found for id: " + id);
                throw new EntityNotFoundException("Theater Not Found with ID: " + id);
            }
            else return Theatre.toResource(theaterEntity.get());

        }

    }
