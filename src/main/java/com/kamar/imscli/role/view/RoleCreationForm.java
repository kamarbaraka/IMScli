package com.kamar.imscli.role.view;

import com.kamar.imscli.role.event.RoleCreationEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.context.ApplicationEventPublisher;

/**
 * form for role creation.
 * @author kamar baraka.*/

@Route("create_role")
public class RoleCreationForm extends VerticalLayout {

    private final ApplicationEventPublisher eventPublisher;

    private final TextField roleField = new TextField("role name");
    private final Button createButton = new Button("create");

    public RoleCreationForm(ApplicationEventPublisher eventPublisher) {
        /*inject dependencies*/
        this.eventPublisher = eventPublisher;

        /*configure layout*/
        this.setAlignItems(Alignment.CENTER);
        this.add(getRoleField(), getCreateButton());
    }

    private TextField getRoleField(){
        /*configure the role field*/
        roleField.setRequired(true);
        roleField.setRequiredIndicatorVisible(true);
        roleField.setAutoselect(true);
        roleField.setAutocorrect(true);
        roleField.setTooltipText("enter the role name");
        roleField.setClearButtonVisible(true);
        roleField.setValueChangeMode(ValueChangeMode.EAGER);

        roleField.addValueChangeListener(event -> {
            createButton.setEnabled(true);
            createButton.getStyle().setBackground("green");
            createButton.getStyle().setColor("white");
        });

        return roleField;
    }

    private Button getCreateButton(){
        /*configure the create button*/
        createButton.setEnabled(false);

        createButton.addClickListener(event -> {
            /*create and publish the event*/
            RoleCreationEvent roleCreationEvent = new RoleCreationEvent(this, roleField);
            eventPublisher.publishEvent(roleCreationEvent);
        });

        return createButton;
    }
}
