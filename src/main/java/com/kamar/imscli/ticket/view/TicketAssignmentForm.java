package com.kamar.imscli.ticket.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * the form for assigning a ticket.
 * @author kamar baraka.*/


@Route("ticket_assignment")
@RequiredArgsConstructor
public class TicketAssignmentForm extends VerticalLayout {

    private final ComboBox<String> assignToField;
    private final DatePicker deadlineField;
    private final Button assignButton;

    /**
     * Constructs a new TicketAssignmentForm object.
     *
     * This constructor initializes the form by injecting dependencies, initializing
     * the necessary components, and configuring the layout.
     *
     * The TicketAssignmentForm contains three components:
     * - assignToField: ComboBox for selecting a user to assign the ticket to.
     * - deadlineField: DatePicker for selecting a deadline for the ticket.
     * - assignButton: Button for assigning the ticket to the selected user.
     *
     * Usage:
     * TicketAssignmentForm form = new TicketAssignmentForm();
     */
    public TicketAssignmentForm() {
        /*inject dependencies*/

        /*initialize the components*/
        this.assignToField = new ComboBox<>();
        this.deadlineField = new DatePicker();
        this.assignButton = new Button();

        /*configure the layout*/
        getLayout();
        initListeners();
    }

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

    /**
     * This method configures the assign button in a Button widget.
     * It sets the button as disabled.
     *
     * @return the configured assignButton as a Button
     */
    private Button getAssignButton(){
        /*configure the assign button*/
        assignButton.setEnabled(false);
        assignButton.setText("assign");

        return assignButton;
    }

    /**
     * This method initializes the listeners for the assignToField, deadlineField, and assignButton.
     * These listeners are added to handle value change events and button click events respectively.
     * The listeners are defined as lambda expressions.
     */
    private void initListeners(){
        /*add the listeners*/
        assignToField.addValueChangeListener(event -> {
            deadlineField.setEnabled(true);
        });

        deadlineField.addValueChangeListener(event -> {
            assignButton.setEnabled(true);
        });

        assignButton.addClickListener(event -> {});
    }

    /**
     * This method configures the layout of the current component.
     * It adds the assignToField, deadlineField, and assignButton to the layout.
     * The layout is aligned to the center.
     */
    private void getLayout(){
        /*configure the layout*/
        this.add(
                getAssignToField(),
                getDeadlineField(),
                getAssignButton()
        );

        this.setAlignItems(Alignment.CENTER);
    }

}
