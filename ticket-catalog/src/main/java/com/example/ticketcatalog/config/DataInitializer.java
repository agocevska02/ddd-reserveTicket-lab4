package com.example.ticketcatalog.config;


import com.example.sharedkernel.domain.base.financial.Currency;
import com.example.sharedkernel.domain.base.financial.Money;
import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.domain.repository.TicketRepository;
import com.example.ticketcatalog.domain.valueobjects.TicketType;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final TicketRepository ticketRepository;

    @PostConstruct
    public void initData(){
        if(ticketRepository.findAll().isEmpty()){
            ticketRepository.saveAndFlush(Ticket.build(TicketType.WALKING,Money.valueOf(Currency.MKD,100),5));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.DRIVE_ONLY,Money.valueOf(Currency.MKD,120),3));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.DRIVE_ONLY,Money.valueOf(Currency.MKD,120),5));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.DRIVE_ONLY,Money.valueOf(Currency.MKD,120),6));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.WALKING,Money.valueOf(Currency.MKD,100),2));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.WALKING,Money.valueOf(Currency.MKD,100),3));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.WALKING,Money.valueOf(Currency.MKD,100),6));
            ticketRepository.saveAndFlush(Ticket.build(TicketType.WALKING,Money.valueOf(Currency.MKD,100),7));

        }

    }
}