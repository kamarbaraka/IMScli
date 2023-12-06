package com.kamar.imscli.ticket.proxy;

import com.kamar.imscli.ticket.data.dto.TicketAssignmentDto;
import com.kamar.imscli.ticket.exception.TicketException;

/**
 * proxy for assigning tickets.
 * @author kamar baraka.*/

public interface TicketAssignmentProxy {

    void assignATicket(TicketAssignmentDto assignmentDto) throws TicketException;
}
