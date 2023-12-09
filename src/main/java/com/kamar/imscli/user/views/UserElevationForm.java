package com.kamar.imscli.user.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * form to elevate a user.
 * @author kamar baraka.*/

@Route("user_elevation")
public class UserElevationForm extends VerticalLayout {


    private final ComboBox<String > roleField;
    private final ComboBox<String> userField;
    private final Checkbox elevateField;
    private final Button elevateButton;


    public UserElevationForm() {
        /*inject dependencies*/

        /*initialize the components*/
        this.roleField = new ComboBox<>();
        this.elevateField = new Checkbox();
        this.userField = new ComboBox<>();
        this.elevateButton = new Button();

        /*configure layout*/
        configureLayout();
    }

    private void configureLayout() {

        configureRoleField();
        configureElevateField();
        configureUserField();
        configureElevateButton();

        initListeners();

        this.add(roleField, userField, elevateField, elevateButton);
        this.setAlignItems(Alignment.CENTER);
    }

    private void configureRoleField() {
        /*configure the role field*/
        roleField.setLabel("role to elevate/demote");
        roleField.setRequired(true);
        roleField.setRequiredIndicatorVisible(true);
        roleField.setAutofocus(true);
        roleField.setClearButtonVisible(true);
        roleField.setMinWidth("500px");
        roleField.setErrorMessage("provide a role");
        roleField.setPlaceholder("select a role");
        roleField.setItems("role 1", "role2", "role3", "role 4");

    }

    private void configureUserField() {
        /*configure the user field*/
        userField.setLabel("user to elevate/demote");
        userField.setEnabled(false);
        userField.setRequired(true);
        userField.setRequiredIndicatorVisible(true);
        userField.setClearButtonVisible(true);
        userField.setMinWidth("500px");
        userField.setErrorMessage("provide a user");
        userField.setPlaceholder("select a user");
        userField.setItems("user 1", "user 2", "user 3", "user 4");

    }

    private void configureElevateField() {
        /*configure the elevated checkbox*/
        elevateField.setLabel("demote");
        elevateField.setEnabled(false);
        elevateField.setTooltipText("check to demote, uncheck to elevate");

    }

    private void configureElevateButton() {
        /*configure the elevate button*/
        elevateButton.setText("elevate");
        elevateButton.setEnabled(false);
        elevateButton.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_UP));
        elevateButton.setMinWidth("300px");

    }

    private void initListeners() {
        /*configure listeners*/
        roleField.addValueChangeListener(event -> {
            userField.setEnabled(true);
        });

        userField.addValueChangeListener(event -> {
            elevateField.setEnabled(true);
            elevateField.setLabel("demote");
            elevateButton.getStyle().setBackground("green");
            elevateButton.getStyle().setColor("white");
            elevateButton.setText("elevate");
            elevateButton.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_UP));
            elevateButton.setEnabled(true);

        });

        elevateField.addValueChangeListener(event -> {

            if (elevateField.getValue()) {
                elevateField.setLabel("elevate");
                elevateButton.getStyle().setBackground("red");
                elevateButton.getStyle().setColor("white");
                elevateButton.setText("demote");
                elevateButton.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_DOWN));
                elevateButton.setEnabled(true);
            }else {
                elevateField.setLabel("demote");
                elevateButton.getStyle().setBackground("green");
                elevateButton.getStyle().setColor("white");
                elevateButton.setText("elevate");
                elevateButton.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_UP));
                elevateButton.setEnabled(true);
            }

        });

        elevateButton.addClickListener(event -> {
            if (roleField.isEmpty() || userField.isEmpty()) {
                Notification.show("fill all required inputs");
                return;
            }
            Notification.show("clicked");
        });

    }



}
