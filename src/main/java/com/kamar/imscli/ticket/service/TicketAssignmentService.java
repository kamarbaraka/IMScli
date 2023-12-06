package com.kamar.imscli.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * the service for assigning a ticket.
 * @author kamar baraka.*/


public interface TicketAssignmentService {

    void assignATicket();
    List<String> getUsersToAssign();
}
