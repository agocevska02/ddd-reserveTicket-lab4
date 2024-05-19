package com.example.ordermanagment.domain.model;

import com.example.ordermanagment.domain.valueobjects.Ticket;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.financial.Currency;
import com.example.sharedkernel.domain.base.financial.Money;
import jakarta.persistence.*;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;


import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {


    private Instant orderedOn;
    @Column(name = "order_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TicketInOrder> ticketInOrderList = new HashSet<>();

    public Order() {
       super(OrderId.randomId(OrderId.class));
    }
    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public Money total() {
        return ticketInOrderList.stream().map(TicketInOrder::subtotal).reduce(new Money(currency, 0), Money::add);
    }
    public TicketInOrder  addTicket(@NotNull Ticket ticket, int qty, LocalDate date){
        Objects.requireNonNull(ticket,"ticket must not be null");
        var item = new TicketInOrder(ticket.getId(),ticket.getPrice(),qty,date);
        ticketInOrderList.add(item);
        return item;
    }
    public void removeTicket(@NotNull TicketInOrderId ticketInOrderId){
        Objects.requireNonNull(ticketInOrderId,"ticket must not be null");

        ticketInOrderList.removeIf(v->v.getId().equals(ticketInOrderId));
    }

}
