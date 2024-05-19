package com.example.ticketcatalog.xport.events;

import com.example.sharedkernel.domain.base.events.DomainEvent;
import com.example.sharedkernel.domain.base.events.orders.TicketInOrderCreated;
import com.example.sharedkernel.domain.config.config.TopicHolder;
import com.example.ticketcatalog.domain.models.TicketId;
import com.example.ticketcatalog.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketListener {
    private final TicketService ticketService;
    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED,groupId = "ticketCatalog")
    public void consumerTicketInOrderCreatedEvent(String jsonMessage){
        try{
            TicketInOrderCreated event = DomainEvent.fromJson(jsonMessage, TicketInOrderCreated.class);
            ticketService.ticketInOrderCreated(TicketId.of(event.getTicketId()),event.getQuantity());
        }
        catch (Exception e){

        }
    }
    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_REMOVED,groupId = "ticketCatalog")
    public void consumerTicketInOrderRemovedEvent(String jsonMessage){
        try{
            TicketInOrderCreated event = DomainEvent.fromJson(jsonMessage, TicketInOrderCreated.class);
            ticketService.ticketInOrderRemoved(TicketId.of(event.getTicketId()),event.getQuantity());
        }
        catch (Exception e){

        }
    }
}
