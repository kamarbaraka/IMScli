package com.kamar.imscli.ticket.proxy;

import com.kamar.imscli.app_props.AppProperties;
import com.kamar.imscli.ticket.data.dto.TicketCreationDto;
import com.kamar.imscli.ticket.exception.TicketException;
import com.kamar.imscli.ticket.model.Ticket;
import com.vaadin.flow.component.upload.receivers.FileData;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.component.upload.receivers.UploadOutputStream;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * implementation of the ticket creation proxy contract.
 * @author kamar baraka.*/

@Service
@Log4j2
@RequiredArgsConstructor
public class TicketCreationProxyImpl implements TicketCreationProxy {

    private final AppProperties appProperties;
    private final RestTemplate restTemplate;

    @Override
    public void postTicket(TicketCreationDto ticketCreationDto) throws TicketException {

        /*prepare the request*/

        /*configure the headers*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        /*create the body*/
        MultiValueMap<String , Object> attachmentBody = new LinkedMultiValueMap<>();

        /*get the attachments buffer*/
        MultiFileMemoryBuffer attachmentBuffer = ticketCreationDto.attachments();

        /*create s store for the byte array resources from the buffer*/
        List<ByteArrayResource> byteArrayResources = new ArrayList<>();

        /*get and store the byte array resources*/
        attachmentBuffer.getFiles().forEach(filename -> {
            InputStream inputStream = attachmentBuffer.getInputStream(filename);
            byte[] bytes = new byte[0];
            try {
                bytes = inputStream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes) {
                @Override
                public String getFilename() {
                    return filename;
                }
            };

            byteArrayResources.add(byteArrayResource);
        });

        /*add the resources to the body*/
        byteArrayResources.forEach(byteArrayResource -> {
            attachmentBody.add("attachments", new HttpEntity<>(byteArrayResource, headers));
        });

        /*create request entity*/
        HttpEntity<MultiValueMap<String , Object>> requestEntity = new HttpEntity<>(attachmentBody, headers);

        /*get data*/
        String department = ticketCreationDto.department();
        String title = ticketCreationDto.title();
        String description = ticketCreationDto.description();

        String ticketCreationUrl = appProperties.resourceServerBaseUrl() + "/api/v1/tickets?department=" + department +
                "&title=" + title + "&description=" + description;

        ResponseEntity<Void> response = restTemplate.postForEntity(ticketCreationUrl, requestEntity, Void.class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.warn(response.getStatusCode());
            throw new TicketException("failed to create ticket");
        }
    }

    @Override
    public Ticket getTicketByTicketNumber(String ticketNumber) throws TicketException {

        /*configure the headers*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        /*configure the url*/
        String encodedTicketNumber = URLEncoder.encode(ticketNumber, StandardCharsets.UTF_8);
        String ticketUrl = appProperties.resourceServerBaseUrl() + "/api/v1/tickets/management/get?ticket_number=" +
                encodedTicketNumber;

        /*send the request*/
        ResponseEntity<Ticket> response = restTemplate.exchange(ticketUrl, HttpMethod.GET, request, Ticket.class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.warn(response.getStatusCode());
        }

        return response.getBody();
    }
}
