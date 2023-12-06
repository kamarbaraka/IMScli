package com.kamar.imscli.ticket.data.dto;

/**
 * the ticket assignment dto.
 * @author kamar baraka.*/

public record TicketAssignmentDto(
        String ticketNumber,
        String to,
        String priority,
        String deadline
) {
}
