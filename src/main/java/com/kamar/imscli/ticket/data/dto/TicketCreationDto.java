package com.kamar.imscli.ticket.data.dto;

import java.io.InputStream;
import java.util.Map;

/**
 * the ticket creation dto.
 * @author kamar baraka.*/

public record TicketCreationDto(
        String department,
        String title,
        String description,
        Map<String , InputStream> attachments
) {
}
