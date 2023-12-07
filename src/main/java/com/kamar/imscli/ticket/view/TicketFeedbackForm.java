package com.kamar.imscli.ticket.view;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocapitalize;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 * the form to submit feedback on a ticket.
 * @author kamar baraka.
 */
@Route("ticket_feedback")
public class TicketFeedbackForm extends VerticalLayout {

    private final TextArea feedbackField;
    private final Button submitButton;
    private final Button ratingButton;
    private final NativeLabel charCountLabel;
    private final HorizontalLayout buttonLayout;

    // Constructor to initialize instance fields
    public TicketFeedbackForm() {
        feedbackField = new TextArea();
        submitButton = new Button("Submit");
        ratingButton = new Button("Rate");
        charCountLabel = new NativeLabel();
        buttonLayout = new HorizontalLayout();

        // Configurations
        configureFeedbackField();
        configureSubmitButton();
        configureRatingButton();
        configureCharCountLabel();
        getButtonLayout();

        /*configure the listeners*/
        initListeners();

        // Add components to the Vertical Layout
        add(feedbackField, buttonLayout);
        this.setAlignItems(Alignment.CENTER);
    }

    private void configureCharCountLabel(){
        charCountLabel.getStyle().setColor("blue");
    }

    // Fluent API method to configure feedbackField
    private void configureFeedbackField() {
        feedbackField.setLabel("feedback");
        feedbackField.setRequired(true);
        feedbackField.setRequiredIndicatorVisible(true);
        feedbackField.setMaxLength(250);
        feedbackField.setMinWidth("500px");
        feedbackField.setAutocapitalize(Autocapitalize.SENTENCES);
        feedbackField.setAutocomplete(Autocomplete.ON);
        feedbackField.setAutocorrect(true);
        feedbackField.setValueChangeMode(ValueChangeMode.EAGER);
        feedbackField.setPlaceholder("Your feedback...");
        feedbackField.setClearButtonVisible(true);
        feedbackField.setTooltipText("enter your feedback");
    }

    // Fluent API method to configure submitButton
    private void configureSubmitButton() {

        submitButton.setEnabled(false);
        submitButton.setMinWidth("150px");
        submitButton.setIcon(new Icon(VaadinIcon.CHECK_CIRCLE));

    }

    // Fluent API method to configure ratingButton
    private void configureRatingButton() {

        ratingButton.setMinWidth("150px");
        ratingButton.setIcon(new Icon(VaadinIcon.STAR));
        ratingButton.getStyle().setBackground("green");
        ratingButton.getStyle().setColor("white");
    }

    private void initListeners(){

        feedbackField.addValueChangeListener(event -> {
            submitButton.getStyle().setBackground("green");
            submitButton.getStyle().setColor("white");
            submitButton.setEnabled(true);

            getCharCount(event, charCountLabel, feedbackField);

            submitButton.getStyle().setBackground("green");
            submitButton.getStyle().setColor("white");
            submitButton.setEnabled(true);
        });

        submitButton.addClickListener(event -> {
            // On click behavior to be implemented
        });

        ratingButton.addClickListener(event -> {
            // On click behavior to be implemented
        });
    }

    private void getButtonLayout(){
        /*configure the button layout*/
        buttonLayout.setAlignItems(Alignment.BASELINE);
        buttonLayout.add(submitButton, ratingButton);
    }

    static void getCharCount(AbstractField.ComponentValueChangeEvent<TextArea, String> event,
                             NativeLabel charCountLabel, TextArea feedbackField) {
        /*get the character count*/
        int charCount = event.getValue().length();
        if (charCount > 200) {
            charCountLabel.getStyle().setColor("red");
        }else {
            charCountLabel.getStyle().setColor("blue");
        }

        charCountLabel.setText(charCount + "/"+ feedbackField.getMaxLength());
        feedbackField.setSuffixComponent(charCountLabel);
    }
}