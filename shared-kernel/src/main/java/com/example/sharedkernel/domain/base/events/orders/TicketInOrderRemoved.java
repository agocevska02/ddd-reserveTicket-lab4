package com.example.sharedkernel.domain.base.events.orders;

import com.example.sharedkernel.domain.config.config.TopicHolder;
import com.example.sharedkernel.domain.base.events.DomainEvent;

import java.time.LocalDate;

public class TicketInOrderRemoved extends DomainEvent {


    private String productId;
    private int quantity;
    private LocalDate date;

    public TicketInOrderRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public TicketInOrderRemoved(String topic, String productId, int quantity, LocalDate date) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.quantity = quantity;
        this.date=date;
    }

}
