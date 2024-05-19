package com.example.sharedkernel.domain.base.events.orders;

import com.example.sharedkernel.domain.config.config.TopicHolder;
import com.example.sharedkernel.domain.base.events.DomainEvent;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TicketInOrderCreated extends DomainEvent {

    private String ticketId;
    private int quantity;
    private LocalDate date_r;
    public TicketInOrderCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public TicketInOrderCreated( String ticketId, int quantity, LocalDate date) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.date_r = date;
    }
}
