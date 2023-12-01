package com.kamar.imscli.ticket.service;

import com.kamar.imscli.ticket.event.TicketCreationEvent;
import com.kamar.imscli.ticket.exception.TicketException;
import com.kamar.imscli.ticket.model.Ticket;

/**
 * ticket creation contract.
 * @author kamar baraka.*/

public interface TicketCreationService {

    void createTicket(TicketCreationEvent event) throws TicketException;

}
