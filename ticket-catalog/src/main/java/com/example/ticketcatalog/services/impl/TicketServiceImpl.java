package com.example.ticketcatalog.services.impl;

import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.domain.models.TicketId;
import com.example.ticketcatalog.domain.repository.TicketRepository;
import com.example.ticketcatalog.services.TicketService;
import com.example.ticketcatalog.services.form.TicketForm;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.ticketcatalog.domain.exception.TicketNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Optional<Ticket> findById(TicketId id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Ticket createTicket(TicketForm form) {
        Ticket t = Ticket.build(form.getTicketType(),form.getMoney(),form.getSales());
        ticketRepository.save(t);
        return t;
    }



    @Override
    public Ticket ticketInOrderCreated(TicketId ticketId, int quantity) {
        Ticket p = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        p.addSales(quantity);
        ticketRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public Ticket ticketInOrderRemoved(TicketId ticketId, int quantity) {
        Ticket p = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        p.removeSales(quantity);
        ticketRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }
}

