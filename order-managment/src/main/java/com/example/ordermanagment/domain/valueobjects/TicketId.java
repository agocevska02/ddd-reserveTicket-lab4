package com.example.ordermanagment.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;

@Embeddable
public class TicketId extends DomainObjectId {
    public TicketId() {
        super(TicketId.randomId(TicketId.class).getId());
    }

    public TicketId(String uuid) {
        super(uuid);
    }

}
