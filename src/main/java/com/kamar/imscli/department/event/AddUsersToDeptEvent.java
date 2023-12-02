package com.kamar.imscli.department.event;

import org.springframework.context.ApplicationEvent;

/**
 * add users to department event.
 * @author kamar baraka.*/

public class AddUsersToDeptEvent extends ApplicationEvent {
    public AddUsersToDeptEvent(Object source) {
        super(source);
    }
}
