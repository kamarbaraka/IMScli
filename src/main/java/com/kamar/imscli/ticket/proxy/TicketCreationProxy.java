package com.kamar.imscli.ticket.proxy;

import com.kamar.imscli.ticket.data.dto.TicketCreationDto;
import com.kamar.imscli.ticket.exception.TicketException;
import com.kamar.imscli.ticket.model.Ticket;

/**
 * ticket creation proxy contract.
 * @author kamar baraka.*/

public interface TicketCreationProxy {

    void postTicket(TicketCreationDto ticketCreationDto) throws TicketException;
    Ticket getTicketByTicketNumber(String ticketNumber) throws TicketException;
}
