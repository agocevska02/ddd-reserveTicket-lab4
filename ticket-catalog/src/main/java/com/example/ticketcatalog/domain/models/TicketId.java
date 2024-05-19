package com.example.ticketcatalog.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class TicketId extends DomainObjectId {
    private TicketId() {
        super(TicketId.randomId(TicketId.class).getId());
    }

    public TicketId(@NonNull String uuid) {
        super(uuid);
    }

    public static TicketId of(String uuid) {
        TicketId p = new TicketId(uuid);
        return p;
    }

}
