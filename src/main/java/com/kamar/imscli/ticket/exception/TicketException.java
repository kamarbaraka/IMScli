package com.kamar.imscli.ticket.exception;

import java.io.IOException;

/**
 * the ticket exception class.
 * @author kamar baraka.*/

public class TicketException extends IOException {

    public TicketException(String message) {
        super(message);
    }
}
