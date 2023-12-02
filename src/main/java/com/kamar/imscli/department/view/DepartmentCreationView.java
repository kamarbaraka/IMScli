package com.kamar.imscli.department.view;

import com.kamar.imscli.department.event.DepartmentCreationEvent;
import com.kamar.imscli.department.service.DepartmentManagementService;
import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.Autocapitalize;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationEventPublisher;

/**
 * the department creation view.
 * @author kamar baraka.*/

@Route("create_department")
public class DepartmentCreationView extends VerticalLayout {

    private final UserService userService;
    private final  ApplicationEventPublisher eventPublisher;

    private final TextField departmentNameField = new TextField("department name");
    private final TextField emailField = new TextField("department email");
    private final Select<String > headOfDepartmentField = new Select<>();
    private final Button createDeptButton = new Button("create");

    public DepartmentCreationView(UserService userService,
                                  ApplicationEventPublisher eventPublisher) {

        /*inject dependencies*/
        this.userService = userService;
        this.eventPublisher = eventPublisher;

        /* configure the layout*/
        this.setAlignItems(Alignment.CENTER);
        this.add(getDepartmentNameField(), getEmailField(), getHeadOfDepartmentField(), getCreateDeptButton());

    }

    private TextField getDepartmentNameField(){
        /*configure the department name field*/
        departmentNameField.setRequired(true);
        departmentNameField.setRequiredIndicatorVisible(true);
        departmentNameField.setClearButtonVisible(true);
        departmentNameField.setErrorMessage("provide department name");
        departmentNameField.setAutoselect(true);
        departmentNameField.setAutocapitalize(Autocapitalize.CHARACTERS);
        departmentNameField.setTooltipText("enter department name");
        departmentNameField.setValueChangeMode(ValueChangeMode.EAGER);

        departmentNameField.addValueChangeListener(listener -> {
            emailField.setEnabled(true);
        });

        return departmentNameField;
    }

    private TextField getEmailField(){
        /*configure the email field*/
        emailField.setEnabled(false);
        emailField.setRequired(true);
        emailField.setRequiredIndicatorVisible(true);
        emailField.setClearButtonVisible(true);
        emailField.setAutoselect(true);
        emailField.setAutocomplete(Autocomplete.EMAIL);
        emailField.setTooltipText("enter the email of the department");
        emailField.setErrorMessage("provide department email");
        emailField.setValueChangeMode(ValueChangeMode.EAGER);

        emailField.addValueChangeListener(listener -> {
            headOfDepartmentField.setEnabled(true);
        });

        return emailField;
    }

    private Select<String > getHeadOfDepartmentField(){
        /*configure the head of department field*/
        headOfDepartmentField.setEnabled(false);
        headOfDepartmentField.setEmptySelectionCaption("provide the head of department");
        headOfDepartmentField.setErrorMessage("provide HOD");
        headOfDepartmentField.setItems(userService.getAllUsersWithAuthorityAsString("employee"));

        headOfDepartmentField.addValueChangeListener(listener -> {
            createDeptButton.setEnabled(true);
            createDeptButton.getStyle().setBackground("green");
            createDeptButton.getStyle().setColor("white");
        });

        return headOfDepartmentField;
    }

    private Button getCreateDeptButton(){
        /*configure the create dept button*/
        createDeptButton.setEnabled(false);

        createDeptButton.addClickListener(event -> {
            /*create and publish the event*/
            DepartmentCreationEvent departmentCreationEvent = new DepartmentCreationEvent(this, departmentNameField,
                    emailField, headOfDepartmentField);

            eventPublisher.publishEvent(departmentCreationEvent);
        });

        return createDeptButton;
    }
}
