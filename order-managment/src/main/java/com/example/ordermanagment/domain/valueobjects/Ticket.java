package com.example.ordermanagment.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.base.financial.Currency;
import com.example.sharedkernel.domain.base.financial.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class Ticket implements ValueObject {
    private final TicketId id;
    private  final Money price;
    private final TicketType ticketType;
    private final int sales;

    private Ticket(){
        this.id=new TicketId();
        this.price=Money.valueOf(Currency.MKD,0);
        this.ticketType=TicketType.WALKING;
        this.sales=0;
    }

    @JsonCreator
    public Ticket(TicketId id, Money price, TicketType ticketType,int sales) {
        this.id = id;
        this.price = price;
        this.ticketType = ticketType;
        this.sales=sales;
    }
}
