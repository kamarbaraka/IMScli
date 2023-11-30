package com.kamar.imscli.ticket.event;

import org.springframework.context.ApplicationEvent;

/**
 * the ticket creation event.
 * @author kamar baraka.*/


public class TicketCreationEvent extends ApplicationEvent {


    public TicketCreationEvent(Object source) {
        super(source);
    }
}
