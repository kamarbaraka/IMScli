package com.kamar.imscli.user.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
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
    private final Button submitButton;
    private final HorizontalLayout titleBar;
    private final HorizontalLayout contentLayout;


    public UserElevationForm() {
        /*inject dependencies*/

        /*initialize the components*/
        this.roleField = new ComboBox<>();
        this.elevateField = new Checkbox();
        this.userField = new ComboBox<>();
        this.submitButton = new Button();
        this.titleBar = new HorizontalLayout();
        this.contentLayout = new HorizontalLayout();

        /*configure layout*/
        configureLayout();
    }

    private void configureLayout() {

        configureRoleField();
    }

    private void configureRoleField() {

    }
}
