package com.kamar.imscli.ticket.listener;

import com.kamar.imscli.ticket.event.TicketAssignmentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * the listener for the ticket assignment event.
 * @author kamar baraka.*/

@Component
@RequiredArgsConstructor
@Log4j2
public class TicketAssignmentEventListener {

    @EventListener(classes = {TicketAssignmentEvent.class})
    void assignTicket(TicketAssignmentEvent event){}
}
