package com.kamar.imscli.ticket.service;

import com.kamar.imscli.ticket.data.dto.AttachmentFile;
import com.kamar.imscli.ticket.data.dto.TicketCreationDto;
import com.kamar.imscli.ticket.event.TicketCreationEvent;
import com.kamar.imscli.ticket.exception.TicketException;
import com.kamar.imscli.ticket.proxy.TicketCreationProxy;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.receivers.FileData;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * implementation of the ticket creation contract.
 * @author kamar baraka.*/

@Service
@Log4j2
@RequiredArgsConstructor
public class TicketCreationServiceImpl implements TicketCreationService {

    private final TicketCreationProxy ticketCreationProxy;

    @EventListener(classes = {TicketCreationEvent.class})
    @Override
    public void createTicket(TicketCreationEvent event) {

        /*get data*/
        String department = event.getDepartmentField().getValue();
        String title = event.getTitleField().getValue();
        String description = event.getDescriptionField().getValue();
        MultiFileMemoryBuffer buffer = event.getMultiFileMemoryBuffer();

        List<Map<String, InputStream>> attachedFiles = buffer.getFiles().stream().map(filename -> {
            Map<String, InputStream> fileAttachments = new HashMap<>();
            FileData fileData = buffer.getFileData(filename);
            InputStream inputStream = buffer.getInputStream(filename);
            fileAttachments.put(filename, inputStream);
            return fileAttachments;
        }).toList();

        /*store data*/
        TicketCreationDto ticketCreationDto = new TicketCreationDto(department, title, description,
                attachedFiles.get(0));

        try {
            /*post data*/
            ticketCreationProxy.postTicket(ticketCreationDto);
            /*redirect*/
            UI.getCurrent().navigate("");
        } catch (TicketException e) {

            /*log and notify*/
            log.warn(e.getMessage());
            Notification.show(e.getMessage());
        }
    }
}
