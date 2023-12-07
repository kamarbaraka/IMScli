package com.kamar.imscli.ticket.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocapitalize;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 * a form to enable an employee to refer a ticket to a fellow employee.
 * @author kamar baraka.*/

@Route("ticket_refer_to")
public class TicketReferralForm extends VerticalLayout {

    private final ComboBox<String > referToField;
    private final TextArea reasonField;
    private final Button referButton;
    private final NativeLabel charLengthLabel;
    private final Button closeButton;

    public TicketReferralForm() {
        /*inject dependencies*/
        referToField = new ComboBox<>();
        reasonField = new TextArea();
        referButton = new Button();
        charLengthLabel = new NativeLabel();
        this.closeButton = new Button();

        /*configure layout*/
        configureLayout();
    }

    /**
     * Configures the referToField component.
     * This method sets the label, required status, autofocus, clear button visibility,
     * min width, error message, placeholder, and items of the referToField.
     */
    private void configureReferToField(){

        referToField.setLabel("refer to:");
        referToField.setRequired(true);
        referToField.setRequiredIndicatorVisible(true);
        referToField.setAutofocus(true);
        referToField.setClearButtonVisible(true);
        referToField.setMinWidth("500px");
        referToField.setErrorMessage("select an employee to refer.");
        referToField.setPlaceholder("select employee");
        referToField.setItems("employee 1", "employee2", "employee3");

    }

    /**
     * Configures the reasonField component.
     * This method sets the label, enabled status, required status, required indicator visibility,
     * autocorrect, autocomplete, autocapitalize, max length, value change mode, placeholder,
     * and min height of the reasonField.
     */
    private void configureReasonField(){

        reasonField.setLabel("reason:");
        reasonField.setEnabled(false);
        reasonField.setRequired(true);
        reasonField.setRequiredIndicatorVisible(true);
        reasonField.setAutocorrect(true);
        reasonField.setAutocomplete(Autocomplete.ON);
        reasonField.setAutocapitalize(Autocapitalize.SENTENCES);
        reasonField.setMaxLength(250);
        reasonField.setValueChangeMode(ValueChangeMode.EAGER);
        reasonField.setMinWidth("500px");
        reasonField.setPlaceholder("reason to refer...");
        reasonField.setMinHeight("200px");
    }

    /**
     * Configures the refer button.
     * This method injects dependencies, initializes components, and configures layout.
     */
    private void configureReferButton(){

        referButton.setText("refer");
        referButton.setEnabled(false);
        referButton.setIcon(new Icon(VaadinIcon.FORWARD));
        referButton.setMinWidth("300px");

    }

    private void configureCloseButton(){

        closeButton.setIcon(new Icon(VaadinIcon.CLOSE));
        closeButton.getStyle().setBackground("red");
        closeButton.getStyle().setColor("white");

    }

    /**
     * Initializes the event listeners for the referToField, reasonField,
     * and referButton components.
     */
    private void initListeners(){

        referToField.addValueChangeListener(event -> {

            reasonField.setEnabled(true);
        });

        reasonField.addValueChangeListener(event -> {

            int charLength = event.getValue().length();
            if (charLength > 200) {
                charLengthLabel.getStyle().setColor("red");
            }else {
                charLengthLabel.getStyle().setColor("blue");
            }

            charLengthLabel.setText(charLength+ "/"+ reasonField.getMaxLength());
            reasonField.setSuffixComponent(charLengthLabel);

            referButton.getStyle().setBackground("green");
            referButton.getStyle().setColor("white");
            referButton.setEnabled(true);
        });

        referButton.addClickListener(event -> {
            Notification.show("clicked");
        });
    }

    /**
     *
     * This method configures the layout of the TicketReferralForm.
     * It sets the alignment to center, configures the referToField, reasonField, referButton,
     * and initializes listeners. Finally, it adds the components to the layout.
     */
    private void configureLayout(){
        /*configure layout*/
        configureReferToField();
        configureReasonField();
        configureReferButton();


        /*configure listeners*/
        initListeners();

        /*add the components*/
        this.setAlignItems(Alignment.CENTER);
        this.add(referToField, reasonField, referButton);
    }

}
