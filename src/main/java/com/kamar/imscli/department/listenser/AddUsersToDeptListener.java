package com.kamar.imscli.department.listenser;

import com.kamar.imscli.department.data.AddUsersToDeptDto;
import com.kamar.imscli.department.event.AddUsersToDeptEvent;
import com.kamar.imscli.department.exception.DepartmentException;
import com.kamar.imscli.department.proxy.AddUserToDeptProxy;
import com.kamar.imscli.user.views.UserLoginForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * the listener for the event to add an employee to a department.
 * @author kamar baraka.*/


@Service
@Log4j2
@RequiredArgsConstructor
public class AddUsersToDeptListener {

    private final AddUserToDeptProxy addUserToDeptProxy;

    @EventListener(classes = {AddUsersToDeptEvent.class})
    public void addUserToDept(AddUsersToDeptEvent event){

        /*get the data*/
        String department = event.getDepartmentField().getValue();
        String employee = event.getEmployeeField().getValue();

        AddUsersToDeptDto dto = new AddUsersToDeptDto(List.of(employee), department);

        try {
            /*add the user to the department and navigate*/
            addUserToDeptProxy.addUsersToDept(dto);
            UI.getCurrent().navigate(UserLoginForm.class);

        } catch (DepartmentException e) {

            /*log and notify*/
            log.error(e.getMessage());
            Notification.show(e.getMessage());
        }
    }
}
