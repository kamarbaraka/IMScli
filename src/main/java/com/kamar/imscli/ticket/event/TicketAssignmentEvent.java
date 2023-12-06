package com.kamar.imscli.ticket.event;

import com.kamar.imscli.ticket.data.dto.TicketAssignmentDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * the event for ticket assignment.
 * @author kamar baraka.*/

@Getter
public class TicketAssignmentEvent extends ApplicationEvent {

    private TicketAssignmentDto assignmentDto;

    public TicketAssignmentEvent(Object source,
                                 TicketAssignmentDto assignmentDto) {
        super(source);
        this.assignmentDto = assignmentDto;
    }
}
