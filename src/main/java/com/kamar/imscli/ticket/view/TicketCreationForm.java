package com.kamar.imscli.ticket.view;

import com.kamar.imscli.department.service.DepartmentManagementService;
import com.kamar.imscli.ticket.event.TicketCreationEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
 * Represents the ticket creation form view.
 * @author kamar baraka.
 */

@Route("create_ticket")
public class TicketCreationForm extends VerticalLayout {

    private final ApplicationEventPublisher eventPublisher;
    private final DepartmentManagementService departmentManagementService;
    private final Select<String> departmentField = new Select<>();
    private final TextField titleField = new TextField("about");
    private final TextArea descriptionField = new TextArea("description");
    private final MultiFileMemoryBuffer attachmentBuffer = new MultiFileMemoryBuffer();

    private final Upload fileUpload = new Upload(attachmentBuffer);
    private final Button raiseButton = new Button("raise");
    private final NativeLabel charCountLabel = new NativeLabel();

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

    /**
     * Configures the department field with the required indicator, label, error message, items, and value change listener.
     *
     * @return the configured department field
     */
    private Select<String > getDepartmentField(){

        /*configure the department field*/
        departmentField.setRequiredIndicatorVisible(true);
        departmentField.setLabel("department");
        departmentField.setMinWidth("500px");
        departmentField.setErrorMessage("provide a department!");
        departmentField.setPlaceholder("select a department");

        departmentField.setItems(
                departmentManagementService.getAllDepartmentNames()
        );

        departmentField.addValueChangeListener(listener -> {
            titleField.setEnabled(true);
        });

        return departmentField;
    }

    /**
     * Configures the title field.
     *
     * @return the configured title field
     */
    private TextField getTitleField(){
        /*configure the title field*/
        titleField.setEnabled(false);
        titleField.setRequired(true);
        titleField.setRequiredIndicatorVisible(true);
        titleField.setMinWidth("400px");
        titleField.setErrorMessage("provide a definition!");
        titleField.setPlaceholder("define your issue");
        titleField.setTitle("enter your title");
        titleField.setMaxWidth("500px");
        titleField.setMaxLength(50);
        titleField.setClearButtonVisible(true);
        titleField.setValueChangeMode(ValueChangeMode.EAGER);

        titleField.addValueChangeListener(listener -> {
            int charLength = listener.getValue().length();

            if (charLength > 40) {
                charCountLabel.getStyle().setColor("red");
            }else {
                charCountLabel.getStyle().setColor("blue");
            }

            charCountLabel.setText(charLength + "/"+ titleField.getMaxLength());
            titleField.setSuffixComponent(charCountLabel);
            descriptionField.setEnabled(true);
        });

        return titleField;
    }

    /**
     * Configures the description field.
     *
     * @return the configured description field
     */
    private TextArea getDescriptionField(){
        /*configure the description field*/
        descriptionField.setEnabled(false);
        descriptionField.setRequired(true);
        descriptionField.setMinWidth("500px");
        descriptionField.setMaxLength(500);
        descriptionField.setPlaceholder("description...");
        descriptionField.setRequiredIndicatorVisible(true);
        descriptionField.setErrorMessage("provide description!");
        descriptionField.setAutocomplete(Autocomplete.ON);
        descriptionField.setValueChangeMode(ValueChangeMode.EAGER);

        descriptionField.addValueChangeListener(listener -> {

            TicketFeedbackForm.getCharCount(listener, charCountLabel, descriptionField);

            raiseButton.setEnabled(true);
            raiseButton.getStyle().setColor("white");
            raiseButton.getStyle().setBackground("green");
        });

        return descriptionField;
    }

    /**
     * Configures the file upload component.
     *
     * @return the configured file upload component
     */
    private Upload getFileUpload(){
        /*configure file upload*/
        fileUpload.setAutoUpload(true);
        fileUpload.setMaxFileSize(10_000_000);
        fileUpload.setAcceptedFileTypes("image/jpeg", "image/png", "application/pdf");
        fileUpload.setMinWidth("500px");

        fileUpload.addSucceededListener(listener -> {

            Notification.show("file uploaded.", 2000, Notification.Position.MIDDLE);
        });

        fileUpload.addFinishedListener(listener -> {
            Notification.show("uploaded successfully");
        });

        return fileUpload;
    }

    /**
     * Configures the raise button component.
     *
     * @return the configured raise button component
     */
    private Button getRaiseButton(){
        /*configure the raise button*/
        raiseButton.setEnabled(false);
        raiseButton.setMinWidth("300px");
        raiseButton.setIcon(new Icon(VaadinIcon.HAND));

        raiseButton.addClickListener(listener -> {
            /*create and publish event*/
            TicketCreationEvent ticketCreationEvent = new TicketCreationEvent(this, departmentField, titleField,
                    descriptionField, attachmentBuffer);
            eventPublisher.publishEvent(ticketCreationEvent);
        });

        return raiseButton;
    }
}
