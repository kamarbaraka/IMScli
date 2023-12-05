package com.kamar.imscli.department.view;

import com.kamar.imscli.department.event.AddUsersToDeptEvent;
import com.kamar.imscli.department.service.DepartmentManagementService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationEventPublisher;

/**
 * form to add users to department.
 * @author kamar baraka.*/

@Route("add_users")
public class AddUsersToDeptForm extends VerticalLayout {

    private final ApplicationEventPublisher eventPublisher;
    private final DepartmentManagementService departmentManagement;

    private final Select<String> departmentField = new Select<>();
    private final  Select<String> employeeField = new Select<>();

    private final Button addButton = new Button("add");

    public AddUsersToDeptForm(ApplicationEventPublisher eventPublisher,
                              DepartmentManagementService departmentManagementService) {
        /*inject dependencies*/
        this.eventPublisher = eventPublisher;
        this.departmentManagement = departmentManagementService;

        /*configure layout*/
        this.setAlignItems(Alignment.CENTER);
        this.add(getDepartmentField(), getEmployeeField(), getAddButton());
    }

    private Select<String > getDepartmentField(){
        /*configure the department*/
        departmentField.setRequiredIndicatorVisible(true);
        departmentField.setLabel("Department");
        departmentField.setEmptySelectionCaption("select a department");
        departmentField.setItems(departmentManagement.getAllDepartmentNames());

        departmentField.addValueChangeListener(event -> {
            employeeField.setEnabled(true);
        });

        return departmentField;
    }

    private Select<String> getEmployeeField(){
        /*configure the employee field*/
        employeeField.setEnabled(false);
        employeeField.setRequiredIndicatorVisible(true);
        employeeField.setLabel("employee");
        employeeField.setEmptySelectionCaption("select an employee to add");
        employeeField.setErrorMessage("select an employee");
        employeeField.setItems(departmentManagement.getEmployeesNotInDept());

        employeeField.addValueChangeListener(event -> {
            addButton.setEnabled(true);
            addButton.getStyle().setBackground("green");
            addButton.getStyle().setColor("white");
        });

        return employeeField;
    }

    private Button getAddButton(){
        addButton.setEnabled(false);

        addButton.addClickListener(event -> {
            /*create the event and publish*/
            AddUsersToDeptEvent addToDeptEvent = new AddUsersToDeptEvent(this, departmentField, employeeField);
            eventPublisher.publishEvent(addToDeptEvent);
        });

        return addButton;
    }

}
