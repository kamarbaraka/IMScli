package com.kamar.imscli.ticket.view;

import com.kamar.imscli.department.service.DepartmentManagementService;
import com.kamar.imscli.ticket.event.TicketCreationEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationEventPublisher;

/**
 * the ticket creation view.
 * @author kamar baraka.*/

@Route("create_ticket")
public class TicketCreationForm extends VerticalLayout {

    private final ApplicationEventPublisher eventPublisher;
    private final DepartmentManagementService departmentManagementService;
    private final Select<String> departmentField = new Select<>();
    private final TextField titleField = new TextField("title");
    private final TextArea descriptionField = new TextArea("description");
    private final MultiFileMemoryBuffer attachmentBuffer = new MultiFileMemoryBuffer();

    private final Upload fileUpload = new Upload(attachmentBuffer);
    private final Button raiseButton = new Button("raise");

    public TicketCreationForm(ApplicationEventPublisher eventPublisher,
                              DepartmentManagementService departmentManagementService) {
        /*inject dependencies*/
        this.eventPublisher = eventPublisher;
        this.departmentManagementService = departmentManagementService;

        /*configure layout*/
        this.add(
                getDepartmentField(),
                getTitleField(),
                getDescriptionField(),
                getFileUpload(),
                getRaiseButton()
        );

        this.setAlignItems(Alignment.CENTER);
    }

    private Select<String > getDepartmentField(){

        /*configure the department field*/
        departmentField.setRequiredIndicatorVisible(true);
        departmentField.setLabel("department");
        departmentField.setErrorMessage("select a department!");
        departmentField.setItems(
                departmentManagementService.getAllDepartmentNames()
        );

        departmentField.addValueChangeListener(listener -> {
            titleField.setEnabled(true);
        });

        return departmentField;
    }

    private TextField getTitleField(){
        /*configure the title field*/
        titleField.setEnabled(false);
        titleField.setRequired(true);
        titleField.setRequiredIndicatorVisible(true);
        titleField.setErrorMessage("provide a title!");
        titleField.setPlaceholder("title");
        titleField.setTitle("enter your title");
        titleField.setClearButtonVisible(true);
        titleField.setValueChangeMode(ValueChangeMode.EAGER);

        titleField.addValueChangeListener(listener -> {
            descriptionField.setEnabled(true);
        });

        return titleField;
    }

    private TextArea getDescriptionField(){
        /*configure the description field*/
        descriptionField.setEnabled(false);
        descriptionField.setRequired(true);
        descriptionField.setRequiredIndicatorVisible(true);
        descriptionField.setErrorMessage("provide description!");
        descriptionField.setAutocomplete(Autocomplete.ON);
        descriptionField.setValueChangeMode(ValueChangeMode.EAGER);

        descriptionField.addValueChangeListener(listener -> {
            raiseButton.setEnabled(true);
            raiseButton.getStyle().setColor("white");
            raiseButton.getStyle().setBackground("green");
        });

        return descriptionField;
    }

    private Upload getFileUpload(){
        /*configure file upload*/
        fileUpload.setAutoUpload(true);
        fileUpload.setMaxFileSize(10_000_000);

        fileUpload.addSucceededListener(listener -> {

            Notification.show("file uploaded.", 2000, Notification.Position.MIDDLE);
        });

        fileUpload.addFinishedListener(listener -> {
            Notification.show("uploaded successfully");
        });

        return fileUpload;
    }

    private Button getRaiseButton(){
        /*configure the raise button*/
        raiseButton.setEnabled(false);

        raiseButton.addClickListener(listener -> {
            /*create and publish event*/
            TicketCreationEvent ticketCreationEvent = new TicketCreationEvent(this, departmentField, titleField,
                    descriptionField, attachmentBuffer);
            eventPublisher.publishEvent(ticketCreationEvent);
        });

        return raiseButton;
    }
}
