package com.kamar.imscli.ticket.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocapitalize;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 * the form to submit a ticket.
 * @author kamar baraka.*/

@Route("ticket_submit")
public class TicketSubmissionForm extends VerticalLayout {

    private final TextArea solutionField;
    private final NativeLabel charCountLabel;
    private final Button submitButton;
    private final Button referButton;

    public TicketSubmissionForm() {
        /*inject dependencies*/

        /*initialize components*/
        solutionField = new TextArea();
        submitButton = new Button();
        referButton = new Button();
        charCountLabel = new NativeLabel();

        /*get the layout*/
        getLayout();
    }

    private TextArea getSolutionField(){
        /*configure the solution field*/
        solutionField.setLabel("solution");
        solutionField.setRequired(true);
        solutionField.setRequiredIndicatorVisible(true);
        solutionField.setAutocomplete(Autocomplete.ON);
        solutionField.setAutocorrect(true);
        solutionField.setWidth("500px");
        solutionField.setTooltipText("enter the solution");
        solutionField.setErrorMessage("provide a solution");
        solutionField.setPlaceholder("Enter your solution here");
        solutionField.setAutocapitalize(Autocapitalize.SENTENCES);
        solutionField.setMaxLength(250);
        solutionField.setValueChangeMode(ValueChangeMode.EAGER);


        return solutionField;
    }

    private NativeLabel getCharCountLabel(){
        /*configure the label*/
        charCountLabel.setMaxHeight("5px");
        charCountLabel.getStyle().setColor("blue");

        return charCountLabel;
    }

    private Button getSubmitButton(){
        /*configure the submit button*/
        submitButton.setText("submit");
        submitButton.setIcon(new Icon(VaadinIcon.CHECK_CIRCLE));
        submitButton.setMinWidth("150px");
        submitButton.setEnabled(false);

        return submitButton;
    }

    private Button getReferButton(){
        /*configure the refer button*/
        referButton.setText("refer");
        referButton.setIcon(new Icon(VaadinIcon.FORWARD));
        referButton.setMinWidth("150px");
        referButton.setEnabled(true);
        referButton.getStyle().setBackground("green");
        referButton.getStyle().setColor("white");

        return referButton;
    }

    private HorizontalLayout getButtonLayout(){
        /*configure the button layout*/
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setAlignItems(Alignment.BASELINE);
        buttonLayout.setBoxSizing(BoxSizing.CONTENT_BOX);

        buttonLayout.add(getSubmitButton(), getReferButton());

        return buttonLayout;
    }

    private void initListeners(){
        /*configure the listeners*/
        solutionField.addValueChangeListener(event -> {
            TicketFeedbackForm.getCharCount(event, charCountLabel, solutionField);

            submitButton.getStyle().setBackground("green");
            submitButton.getStyle().setColor("white");
            submitButton.setEnabled(true);
        });

        submitButton.addClickListener(event -> {});

        referButton.addClickListener(event -> {});
    }

    private void getLayout(){
        /*configure this layout*/
        this.setMinWidth("500px");
        this.setAlignItems(Alignment.CENTER);
        this.setPadding(true);

        initListeners();

        this.add(getSolutionField(), getButtonLayout());
    }
}
