package com.example.ordermanagment.serivce.forms;

import com.example.ordermanagment.domain.valueobjects.Ticket;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TicketInOrderForm {
    @NotNull
    private Ticket ticket;
    @Min(1)
    private int quantity=1;
    @NotNull
    private LocalDate date_of_reservation = LocalDate.now();

}
