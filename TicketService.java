package com.gfg.shoutreview.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.shoutreview.Exception.NotFoundException;
import com.gfg.shoutreview.domain.Show;
import com.gfg.shoutreview.domain.ShowSeat;
import com.gfg.shoutreview.domain.Ticket;
import com.gfg.shoutreview.domain.User;
import com.gfg.shoutreview.repository.ShowRepository;
import com.gfg.shoutreview.repository.TicketRepository;
import com.gfg.shoutreview.repository.UserRepository;
import com.gfg.shoutreview.resource.BookingResource;
import com.gfg.shoutreview.resource.TicketMessage;
import com.gfg.shoutreview.resource.TicketResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketService {

//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private ShowRepository showRepository;
//
//	@Autowired
//	private TicketRepository ticketRepository;
//
//	@Autowired
//	KafkaTemplate<String,String> kafkaTemplate;
//
//	private static String topic="TICKET_BOOKED";
//
//	ObjectMapper mapper=new ObjectMapper();
//
//	public TicketResource bookTicket(BookingResource bookingResource) {
//
//		Optional<User> optionalUser = userRepository.findById(bookingResource.getUserId());
//
//		if (optionalUser.isEmpty()) {
//			throw new NotFoundException("User Not Found with ID: " + bookingResource.getUserId() + " to book ticket");
//		}
//
//		Optional<Show> optionalShow = showRepository.findById(bookingResource.getShowId());
//
//		if (optionalShow.isEmpty()) {
//			throw new NotFoundException("Show Not Found with ID: " + bookingResource.getUserId() + " to book ticket");
//		}
//
//		Set<String> requestedSeats = bookingResource.getSeatsNumbers();
//
//		List<ShowSeat> showSeatsEntities = optionalShow.get().getSeats();
//
//		showSeatsEntities =
//				showSeatsEntities
//						.stream()
//						.filter(seat -> seat.getSeatType().equals(bookingResource.getSeatType())
//								&& !seat.isBooked()
//								&& requestedSeats.contains(seat.getSeatNo()))
//						.collect(Collectors.toList());
//
//		if (showSeatsEntities.size() != requestedSeats.size()) {
//			throw new NotFoundException("Seats Not Available for Booking");
//		}
//
//		Ticket ticket =
//				Ticket.builder()
//						.user(optionalUser.get())
//						.show(optionalShow.get())
//						.seats(showSeatsEntities)
//						.build();
//
//		double amount = 0.0;
//		String allotedSeats = "";
//
//		for (ShowSeat seatsEntity : showSeatsEntities) {
//			seatsEntity.setBooked(true);
//			seatsEntity.setBookedAt(new Date());
//			seatsEntity.setTicket(ticket);
//
//			amount += seatsEntity.getPrice();
//
//			allotedSeats += seatsEntity.getSeatNo() + " ";
//		}
//
//		ticket.setAmount(amount);
//		ticket.setAllottedSeats(allotedSeats);
//
//		if (CollectionUtils.isEmpty(optionalUser.get().getTicketEntities())) {
//			optionalUser.get().setTicketEntities(new ArrayList<>());
//		}
//
//		optionalUser.get().getTicketEntities().add(ticket);
//
//		if (CollectionUtils.isEmpty(optionalShow.get().getTickets())) {
//			optionalShow.get().setTickets(new ArrayList<>());
//		}
//
//		optionalShow.get().getTickets().add(ticket);
//
//		ticket = ticketRepository.save(ticket);
//
//		try {
//			TicketMessage message = new TicketMessage(ticket.getUser().getName(),ticket.getUser().getMobile(),ticket.getUser().getEmail(), ticket.getShow(), ticket.getSeats());
//			log.info("sending kafka message on booking {}", mapper.writeValueAsString(message));
//			kafkaTemplate.send(topic, mapper.writeValueAsString(message));
//		}catch (Exception e){
//				log.error("Exception while sending notification service");
//		}
//
//		return Ticket.toResource(ticket);
//	}
//
//
//	public TicketResource getTicket(long id) {
//		Optional<Ticket> ticketEntity = ticketRepository.findById(id);
//
//		if (ticketEntity.isEmpty()) {
//			log.error("Ticket not found for id: " + id);
//			throw new EntityNotFoundException("Ticket Not Found with ID: " + id);
//		}
//
//		return Ticket.toResource(ticketEntity.get());
//	}
@Autowired
KafkaTemplate<String,String> kafkaTemplate;

	private static String topic="TICKET_BOOKED";

	ObjectMapper mapper=new ObjectMapper();
	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	ShowRepository showRepository;

	@Autowired
	UserRepository userRepository;

	public TicketResource bookTicket(BookingResource bookingResource){
		Optional<User> optionalUser=userRepository.findById(bookingResource.getUserId());
		if(optionalUser.isEmpty()){
			log.info("User id is not valid "+ bookingResource.getUserId());
			throw new NotFoundException("User not found with id "+bookingResource.getUserId()+" to book ticket");
		}
		Optional<Show> optionalShow = showRepository.findById(bookingResource.getShowId());

		if (optionalShow.isEmpty()) {
			throw new NotFoundException("Show Not Found with ID: " + bookingResource.getUserId() + " to book ticket");
		}
		Set<String>requestedSeats=bookingResource.getSeatsNumbers();
		List<ShowSeat>showSeatEntities=optionalShow.get().getSeats();
		showSeatEntities=showSeatEntities.stream().filter(seat->seat.getSeatType().equals(bookingResource.getSeatType())
				&&(!seat.isBooked())&&(requestedSeats.contains(seat.getSeatNo())).(Collectors.toList()));

	}

	public TicketResource getTicket(long id) {
		Optional<Ticket> ticketEntity = ticketRepository.findById(id);

		if (ticketEntity.isEmpty()) {
			log.error("Ticket not found for id: " + id);
			throw new EntityNotFoundException("Ticket Not Found with ID: " + id);
		}

		return Ticket.toResource(ticketEntity.get());
	}
}