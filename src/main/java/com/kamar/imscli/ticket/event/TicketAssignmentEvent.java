package com.kamar.imscli.ticket.event;

import org.springframework.context.ApplicationEvent;

/**
 * the event for ticket assignment.
 * @author kamar baraka.*/


public class TicketAssignmentEvent extends ApplicationEvent {
    public TicketAssignmentEvent(Object source) {
        super(source);
    }
}
