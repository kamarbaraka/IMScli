package com.kamar.imscli.user.views;

import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


/**
 * the user login view.
 * @author kamar baraka.*/

@Route("")
public class UserLoginView extends VerticalLayout {
    private final UserService userService;

    private final TextField usernameField = new TextField("username");
    private final PasswordField passwordField = new PasswordField("password");

    private final Button loginButton = new Button("login");
    private final Button registerButton = new Button("register");

    public UserLoginView(UserService userService) {
        /*inject dependencies*/
        this.userService = userService;

        /*add the components*/
        this.add(getUsernameField(), getPasswordField(), getButtonLayout());

        /*configure this view*/
        this.setAlignItems(Alignment.CENTER);
    }

    private TextField getUsernameField(){

        /*configure the username field*/
        usernameField.setRequired(true);
        usernameField.setRequiredIndicatorVisible(true);
        usernameField.setAutocomplete(Autocomplete.EMAIL);
        usernameField.setErrorMessage("provide your username!");
        usernameField.setPlaceholder("someone@somewhere.com");
        usernameField.setTooltipText("your username");
//        usernameField.setAutofocus(true);
        usernameField.setId("usernameFieldId");
        usernameField.setClearButtonVisible(true);

        usernameField.addValueChangeListener(listener -> {
//            passwordField.focus();
            passwordField.setEnabled(true);
        });

        return usernameField;
    }

    private PasswordField getPasswordField(){

        /*configure the password field and return*/
        passwordField.setEnabled(false);
        passwordField.setRequired(true);
        passwordField.setRequiredIndicatorVisible(true);
        passwordField.setErrorMessage("provide your password!");
//        passwordField.setAutofocus(true);
        passwordField.setPlaceholder("enter password");
        passwordField.setTooltipText("your password");

        passwordField.addValueChangeListener(listener -> {
            loginButton.setEnabled(true);
//            loginButton.setAutofocus(true);
            loginButton.getStyle().setColor("white");
            loginButton.getStyle().setBackground("green");
            registerButton.setEnabled(false);
//            registerButton.setAutofocus(false);
        });

        return passwordField;
    }

    private Button getLoginButton(){

        /*configure the login button and return*/
        loginButton.setEnabled(false);

        /*add a listener*/
        loginButton.addClickListener(listener -> {
            userService.login(this.usernameField, this.passwordField);
        });

        return loginButton;
    }

    private Button getRegisterButton(){

        /*configure the registration button and return*/
        registerButton.setEnabled(true);
//        registerButton.setAutofocus(true);

        /*add listener*/
        registerButton.addClickListener(listener -> {
            UI.getCurrent().navigate(UserRegistrationView.class);
        });

        return registerButton;
    }

    private HorizontalLayout getButtonLayout(){

        /*configure the button layout and return*/
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(getRegisterButton(), getLoginButton());

        buttonLayout.setAlignItems(Alignment.BASELINE);

        return buttonLayout;
    }
}
