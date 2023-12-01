package com.kamar.imscli.user.views;

import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.awt.*;

/**
 * the view for user registration.
 * @author kamar baraka.*/

@Route(value = "register")
public class UserRegistrationView extends VerticalLayout {

    private final UserService userService;

    private final Select<String> roleFiled = new Select<>();
    private final TextField usernameField = new TextField("username");
    private final PasswordField passwordField = new PasswordField("password");

    private final Button loginButton = new Button("login");

    private final Button registerButton = new Button("register");

    public UserRegistrationView(UserService userService) {
        /*inject the user service*/
        this.userService = userService;

        /*add the components*/
        this.add(getRoleFiled(), getUsernameField(), getPasswordField(), getButtonLayout());

        /*configure this view*/
        this.setAlignItems(Alignment.CENTER);
    }

    private Select<String> getRoleFiled(){

        roleFiled.setRequiredIndicatorVisible(true);
        roleFiled.setErrorMessage("provide the department.");
        roleFiled.setLabel("user role");
        roleFiled.setEmptySelectionCaption("please choose a role!");
        roleFiled.setItems(userService.getRoles());

        roleFiled.addValueChangeListener(listener -> {
            usernameField.setEnabled(true);
            usernameField.setAutofocus(true);
        });

        return roleFiled;
    }

    private TextField getUsernameField(){

        /*configure the username field and return*/
        usernameField.setEnabled(false);
        usernameField.setRequired(true);
        usernameField.setRequiredIndicatorVisible(true);
        usernameField.setAutocomplete(Autocomplete.EMAIL);
        usernameField.setErrorMessage("provide your username!");
        usernameField.setAutoselect(true);
        usernameField.setPlaceholder("someone@somewhere.com");
        usernameField.setTooltipText("your username");
        usernameField.setAutofocus(false);
        usernameField.setId("usernameFieldId");
        usernameField.setValueChangeMode(ValueChangeMode.EAGER);

        usernameField.addValueChangeListener(listener -> {
            passwordField.setEnabled(true);
        });

        return usernameField;
    }

    private PasswordField getPasswordField(){

        /*configure the password field and return*/
        passwordField.setEnabled(false);
        passwordField.setRequired(true);
        passwordField.setErrorMessage("provide your password!");
        passwordField.setRequiredIndicatorVisible(true);
        passwordField.setAutofocus(true);
        passwordField.setPlaceholder("enter password");
        passwordField.setTooltipText("your password");
        passwordField.setValueChangeMode(ValueChangeMode.EAGER);

        passwordField.addValueChangeListener(listener -> {
            registerButton.setEnabled(true);
            registerButton.getStyle().setColor("white");
            registerButton.getStyle().setBackground("green");
            loginButton.setEnabled(false);
        });

        return passwordField;
    }

    private Button getRegisterButton(){

        /*configure the registration button and return*/
        registerButton.setEnabled(false);
        registerButton.setAutofocus(true);

        registerButton.addClickListener(listener -> {
            userService.register(this.roleFiled, this.usernameField, this.passwordField);
        });

        return registerButton;
    }

    private Button getLoginButton(){

        /*configure the login button and return*/
        loginButton.setEnabled(true);
        loginButton.setAutofocus(true);

        loginButton.addClickListener(listener -> UI.getCurrent().navigate(UserLoginView.class));

        return loginButton;
    }

    private HorizontalLayout getButtonLayout(){

        /*configure the button layout and return*/
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(getRegisterButton(), getLoginButton());

        buttonLayout.setAlignItems(Alignment.BASELINE);

        return buttonLayout;
    }
}
