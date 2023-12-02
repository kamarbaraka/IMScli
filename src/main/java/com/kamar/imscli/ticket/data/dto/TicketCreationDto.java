package com.kamar.imscli.ticket.data.dto;

import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

/**
 * the ticket creation dto.
 * @author kamar baraka.*/

public record TicketCreationDto(
        String department,
        String title,
        String description,
        MultiFileMemoryBuffer attachments
) {
}
