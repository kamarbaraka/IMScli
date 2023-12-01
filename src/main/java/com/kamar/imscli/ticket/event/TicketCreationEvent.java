package com.kamar.imscli.ticket.event;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


/**
 * the ticket creation event.
 * @author kamar baraka.*/


@Getter
public class TicketCreationEvent extends ApplicationEvent {

    private final Select<String > departmentField;
    private final TextField titleField;
    private final TextArea descriptionField;
    private final MultiFileMemoryBuffer multiFileMemoryBuffer;

    public TicketCreationEvent(Object source,
                               Select<String> departmentField,
                               TextField titleField,
                               TextArea descriptionField,
                               MultiFileMemoryBuffer multiFileMemoryBuffer) {
        super(source);
        this.departmentField = departmentField;
        this.titleField = titleField;
        this.descriptionField = descriptionField;
        this.multiFileMemoryBuffer = multiFileMemoryBuffer;
    }
}
