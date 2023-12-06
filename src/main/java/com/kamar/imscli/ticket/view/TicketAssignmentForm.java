package com.kamar.imscli.ticket.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * the form for assigning a ticket.
 * @author kamar baraka.*/


@Route("ticket_assignment")
@RequiredArgsConstructor
public class TicketAssignmentForm {

    private final ComboBox<String> assignToField;
    private final DatePicker deadlineField;
    private final Button assignButton;


    /**
     * This method configures the assign to field in a ComboBox<String> widget.
     * It sets the required indicator as visible, a placeholder for when no employee is selected,
     * an error message to display when no employee is provided, and a clear button to allow clearing of the selected employee.
     *
     * @return the configured assignToField as a ComboBox<String>
     */
    private ComboBox<String> getAssignToField(){
        /*configure the assign to field*/
        assignToField.setRequiredIndicatorVisible(true);
        assignToField.setLabel("assign to");
        assignToField.setPlaceholder("select an employee");
        assignToField.setErrorMessage("provide the employee to assign.");
        assignToField.setClearButtonVisible(true);

        return assignToField;
    }

    /**
     * This method configures the deadline field in a DatePicker widget.
     * It sets the field as disabled, required indicator as visible, a label to display as "deadline",
     * a minimum allowed date as the current date, initial position as the current date, a placeholder for when no deadline is set,
     * and an error message to display when no deadline is provided.
     *
     * @return the configured deadlineField as a DatePicker
     */
    private DatePicker getDeadlineField(){
        /*configure the deadline*/
        deadlineField.setEnabled(false);
        deadlineField.setRequired(true);
        deadlineField.setRequiredIndicatorVisible(true);
        deadlineField.setLabel("deadline");
        deadlineField.setMin(LocalDate.now());
        deadlineField.setInitialPosition(LocalDate.now());
        deadlineField.setPlaceholder("set a deadline");
        deadlineField.setErrorMessage("provide a deadline");

        return deadlineField;

    }

    private Button getAssignButton(){
        /*configure the assign button*/
        assignButton.setEnabled(false);

        return assignButton;
    }

}
