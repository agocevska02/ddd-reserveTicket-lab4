package com.example.ordermanagment.domain.model;

import com.example.ordermanagment.domain.valueobjects.TicketId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.base.financial.Currency;
import com.example.sharedkernel.domain.base.financial.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "ticket_in_order")
@Getter
public class TicketInOrder extends AbstractEntity<TicketInOrderId> {

    private Money ticketPrice;

    @Column( name = "qty",nullable = false)
    private int quantity;
    @Column(nullable = false)
    private LocalDate date_of_reservation;
    @AttributeOverride(name = "id",column = @Column(name = "ticket_id",nullable = false))
    private TicketId ticketId;
    public Money subtotal(){
        return ticketPrice.multiply(quantity);
    }

    public TicketInOrder() {
        super(DomainObjectId.randomId(TicketInOrderId.class));

    }

    public TicketInOrder(@NotNull TicketId ticketId, @NotNull Money ticketPrice, int qty, @NotNull LocalDate date_of_reservation){
        super(DomainObjectId.randomId(TicketInOrderId.class));
        this.ticketId=ticketId;
        this.ticketPrice=ticketPrice;
        this.quantity=qty;
        this.date_of_reservation=date_of_reservation;
    }
}
