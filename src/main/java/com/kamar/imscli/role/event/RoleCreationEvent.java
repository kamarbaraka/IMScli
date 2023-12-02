package com.kamar.imscli.role.event;

import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * the role creation event.
 * @author kamar baraka.*/

@Getter
public class RoleCreationEvent extends ApplicationEvent {
    private final TextField roleField;

    public RoleCreationEvent(Object source,
                             TextField roleField) {
        super(source);
        this.roleField = roleField;
    }
}
