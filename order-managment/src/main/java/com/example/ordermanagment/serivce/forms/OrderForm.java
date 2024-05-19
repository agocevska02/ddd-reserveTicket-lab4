package com.example.ordermanagment.serivce.forms;

import com.example.ordermanagment.domain.model.TicketInOrder;
import com.example.ordermanagment.domain.model.TicketInOrderId;
import lombok.Data;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import com.example.sharedkernel.domain.base.financial.Currency;
import java.util.List;

@Data
public class OrderForm {
    @NotNull
    private Currency currency;
    @Valid
    @NotEmpty
    private List<TicketInOrderForm> items= new ArrayList<>();


}
