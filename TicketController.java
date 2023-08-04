package com.gfg.shoutreview.controller;

import com.gfg.shoutreview.resource.BookingResource;
import com.gfg.shoutreview.resource.TicketResource;
import com.gfg.shoutreview.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;


    @PostMapping("/book")
    public ResponseEntity<TicketResource>bookticket(@RequestBody BookingResource bookingResource){
        log.info("Request Received for ticket booking: " +bookingResource);
        return ResponseEntity.ok(ticketService.bookTicket(bookingResource));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResource>getTicket(@PathVariable("id") @Min(value = 1,message = "id cannot be -ve") long id){
        log.info("Request recieved to get ticket: "+id);
        return ResponseEntity.ok(ticketService.getTicket(id));
    }
}
