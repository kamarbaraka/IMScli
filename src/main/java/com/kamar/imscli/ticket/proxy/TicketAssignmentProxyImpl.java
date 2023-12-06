package com.kamar.imscli.ticket.proxy;

import com.kamar.imscli.app_props.AppProperties;
import com.kamar.imscli.ticket.data.dto.TicketAssignmentDto;
import com.kamar.imscli.ticket.exception.TicketException;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.util.UriEncoder;

import java.net.URI;
import java.net.URLEncoder;

/**
 * implementation of the ticket assignment proxy.
 * @author kamar baraka.*/

@Service
@Log4j2
@RequiredArgsConstructor
public class TicketAssignmentProxyImpl implements TicketAssignmentProxy {
    
    private final RestTemplate restTemplate;
    private final AppProperties appProperties;
    
    @Override
    public void assignATicket(TicketAssignmentDto assignmentDto) throws TicketException {
        
        /*get the data*/
        String ticketNumber = UriEncoder.encode(assignmentDto.ticketNumber());
        String userToAssign = UriEncoder.encode(assignmentDto.to());
        String priority = UriEncoder.encode(assignmentDto.priority());
        String deadline = UriEncoder.encode(assignmentDto.deadline());

        /*get the user credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");

        /*set headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        String url = appProperties.resourceServerBaseUrl() + "/api/v1/tickets/assign?ticket_number=" + ticketNumber +
                "&to=" + userToAssign + "&priority=" + priority + "&deadline=" + deadline;

        /*set the request*/
        RequestEntity<Object> request = new RequestEntity<>(null, headers, HttpMethod.POST, URI.create(url));

        /*get the response*/
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.error(response.getStatusCode());
            throw new TicketException("can't connect to service");
        }

    }
}
