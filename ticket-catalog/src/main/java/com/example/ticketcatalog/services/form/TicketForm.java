package com.example.ticketcatalog.services.form;

import com.example.sharedkernel.domain.base.financial.Money;
import com.example.ticketcatalog.domain.valueobjects.TicketType;
import lombok.Data;

@Data
public class TicketForm {
    private TicketType ticketType;
    private Money money;
    private  int sales;
}
