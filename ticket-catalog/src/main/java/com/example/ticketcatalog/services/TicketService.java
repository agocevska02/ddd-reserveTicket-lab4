package com.example.ticketcatalog.services;

import com.example.ticketcatalog.domain.models.Ticket;
import com.example.ticketcatalog.domain.models.TicketId;
import com.example.ticketcatalog.services.form.TicketForm;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> findById(TicketId id);
    Ticket createTicket(TicketForm form);
    Ticket ticketInOrderCreated(TicketId ticketId,int quantity);
    Ticket ticketInOrderRemoved(TicketId ticketId,int quantity);
    List<Ticket> getAll();
}
