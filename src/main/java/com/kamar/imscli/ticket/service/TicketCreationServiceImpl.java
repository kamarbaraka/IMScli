package com.kamar.imscli.ticket.service;

import com.kamar.imscli.ticket.data.dto.TicketCreationDto;
import com.kamar.imscli.ticket.event.TicketCreationEvent;
import com.kamar.imscli.ticket.exception.TicketException;
import com.kamar.imscli.ticket.proxy.TicketCreationProxy;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

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


        /*store data*/
        TicketCreationDto ticketCreationDto = new TicketCreationDto(department, title, description,
                buffer);

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
