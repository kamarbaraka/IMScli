package com.kamar.imscli.ticket.data.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * the ticket creation dto.
 * @author kamar baraka.*/

public record TicketCreationDto(
        String department,
        String title,
        String description,
        List<MultipartFile> attachments
) {
}
