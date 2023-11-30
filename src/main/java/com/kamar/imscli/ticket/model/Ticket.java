package com.kamar.imscli.ticket.model;

/**
 * the ticket model object.
 * @author kamar baraka.*/

public record Ticket(
        String ticketNumber,
        String title,
        String description,
        String priority,
        String status,
        String raisedBy,
        String departmentAssigned,
        String assignedTo,
        String deadline

) {
}
