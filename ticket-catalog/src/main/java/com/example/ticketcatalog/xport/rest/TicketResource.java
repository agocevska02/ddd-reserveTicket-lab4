package com.example.ticketcatalog.xport.rest;

import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@AllArgsConstructor
public class TicketResource {

    private final TicketService ticketService;
    @GetMapping
    public List<Ticket> getAll(){
        return ticketService.getAll();
    }
}
