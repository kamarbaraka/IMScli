package com.kamar.imscli.department.view;

import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationEventPublisher;

/**
 * form to add users to department.
 * @author kamar baraka.*/

@Route("add_users")
public class AddUsersToDeptForm extends VerticalLayout {

    private final ApplicationEventPublisher eventPublisher;
    private final UserService userService;

    ListBox<String> employeeField = new ListBox<>();

    public AddUsersToDeptForm(ApplicationEventPublisher eventPublisher,
                              UserService userService) {
        /*inject dependencies*/
        this.eventPublisher = eventPublisher;
        this.userService = userService;

        /*configure layout*/
        this.setAlignItems(Alignment.CENTER);
        this.add(getEmployeeField());
    }

    private ListBox<String> getEmployeeField(){
        /*configure the employee field*/
        employeeField.setRequiredIndicatorVisible(true);
        employeeField.setItems(userService.getAllUsersWithAuthorityAsString("employee"));

        return employeeField;
    }
}
