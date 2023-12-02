package com.kamar.imscli.role.listener;

import com.kamar.imscli.role.event.RoleCreationEvent;
import com.kamar.imscli.role.exception.RoleException;
import com.kamar.imscli.role.proxy.RoleCreationProxy;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * listener for the ticket creation event.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleCreationListener {

    private final RoleCreationProxy roleCreationProxy;

    @EventListener(classes = {RoleCreationEvent.class})
    public void createRole(RoleCreationEvent event){
        /*get data*/
        String role = event.getRoleField().getValue();

        try {
            /*create the role*/
            roleCreationProxy.createRole(role);

            /*redirect*/
            UI.getCurrent().navigate("");
        } catch (RoleException e) {
            /*notify*/
            Notification.show(e.getMessage());
        }
    }
}
