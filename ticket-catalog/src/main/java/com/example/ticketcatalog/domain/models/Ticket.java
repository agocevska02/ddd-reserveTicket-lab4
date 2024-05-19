package com.example.ticketcatalog.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.financial.Money;
import com.example.ticketcatalog.domain.valueobjects.TicketType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "ticket")
@Getter
public class Ticket extends AbstractEntity<TicketId> {

    @Enumerated(value = EnumType.STRING)
    private TicketType ticketType;
    int sales;
    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name="currency", column = @Column(name = "price_currency"))
    })
    private Money price;
    public Ticket(){
        super(TicketId.randomId(TicketId.class));
    }
    public static Ticket build(TicketType ticketType,Money money,int sales){
        Ticket t = new Ticket();
        t.ticketType=ticketType;
        t.price=money;
        t.sales=sales;
        return t;

    }
    public void addSales(int qty){
        this.sales=this.sales-qty;
    }
    public void removeSales(int qty){
        this.sales-=qty;
    }


}
