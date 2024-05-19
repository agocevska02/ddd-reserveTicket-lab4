package com.example.ordermanagment.domain.model;

import com.example.sharedkernel.domain.base.DomainObjectId;

public class TicketInOrderId extends DomainObjectId {

    public TicketInOrderId(){
        super(TicketInOrderId.randomId(TicketInOrderId.class).getId());
    }
    public TicketInOrderId(String uuid){
        super(uuid);
    }
}
