package com.kamar.imscli.ticket.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.http.client.MultipartBodyBuilder;

/**
 * the ticket creation view.
 * @author kamar baraka.*/

@Route("ticket")
public class TicketCreationView extends VerticalLayout {

    private final Select<String> departmentField = new Select<>();
    private final TextField titleField = new TextField("title");
    private final TextArea descriptionField = new TextArea("description");
    private final MultiFileMemoryBuffer attachmentFiles = new MultiFileMemoryBuffer();
    private final Upload fileUpload = new Upload(attachmentFiles);
    private final Button raiseButton = new Button("raise");

    public TicketCreationView() {
        /*inject dependencies*/

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
        departmentField.setItems("dept1", "dept2");

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

        return titleField;
    }

    private TextArea getDescriptionField(){
        /*configure the description field*/
        descriptionField.setEnabled(false);
        descriptionField.setRequired(true);
        descriptionField.setRequiredIndicatorVisible(true);
        descriptionField.setErrorMessage("provide description!");
        descriptionField.setAutocomplete(Autocomplete.ON);

        return descriptionField;
    }

    private MultiFileMemoryBuffer getAttachmentFiles(){
        /*configure attachment files*/

        return attachmentFiles;
    }

    private Upload getFileUpload(){
        /*configure file upload*/
        fileUpload.setAutoUpload(true);
        fileUpload.setAcceptedFileTypes("pdf", "png", "docx", "exl", "jpeg");
//        fileUpload.setUploadButton();
        fileUpload.setMaxFileSize(10_000_000);

//        fileUpload.addSucceededListener(listener -> );

        return fileUpload;
    }

    private Button getRaiseButton(){
        /*configure the raise button*/
        raiseButton.setEnabled(false);

        return raiseButton;
    }
}
