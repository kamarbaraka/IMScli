package com.kamar.imscli.user.views;

import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
public class UserLoginForm extends VerticalLayout {
    private final UserService userService;

    private final TextField usernameField = new TextField("username");
    private final PasswordField passwordField = new PasswordField("password");

    private final Button loginButton = new Button("login");
    private final Button registerButton = new Button("register");

    public UserLoginForm(UserService userService) {
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
        usernameField.setAutoselect(true);
        usernameField.setAutocomplete(Autocomplete.EMAIL);
        usernameField.setClearButtonVisible(true);
        usernameField.setClearButtonVisible(true);
        usernameField.setMinWidth("500px");
        usernameField.getStyle().set("font-size", "20px");
        usernameField.setErrorMessage("provide your username!");
        usernameField.setPlaceholder("someone@somewhere.com");
        usernameField.setTooltipText("your username");
        usernameField.setId("usernameFieldId");

        usernameField.addValueChangeListener(listener -> {
            passwordField.setEnabled(true);
        });

        return usernameField;
    }

    private PasswordField getPasswordField(){

        /*configure the password field and return*/
        passwordField.setEnabled(false);
        passwordField.setRequired(true);
        passwordField.setRequiredIndicatorVisible(true);
        passwordField.setMinWidth("500px");
        passwordField.getStyle().set("font-size", "20px");
        passwordField.setErrorMessage("provide your password!");
        passwordField.setAutoselect(true);
        passwordField.setPlaceholder("enter password...");
        passwordField.setTooltipText("enter your password");

        passwordField.addValueChangeListener(listener -> {
            loginButton.getStyle().setBackground("green");
            loginButton.getStyle().setColor("white");
            loginButton.setEnabled(true);
        });

        return passwordField;
    }

    private Button getLoginButton(){

        /*configure the login button and return*/
        loginButton.setEnabled(false);
        loginButton.setIcon(new Icon(VaadinIcon.SIGN_IN));
        loginButton.setMinWidth("200px");

        /*add a listener*/
        loginButton.addClickListener(listener -> {
            userService.login(this.usernameField, this.passwordField);
        });

        return loginButton;
    }

    private Button getRegisterButton(){

        /*configure the registration button and return*/
        registerButton.setEnabled(true);
        registerButton.getStyle().setBackground("green");
        registerButton.getStyle().setColor("white");
        registerButton.setIcon(new Icon(VaadinIcon.USER));
//        registerButton.setMinHeight("50px");
        registerButton.setMinWidth("200px");

        /*add listener*/
        registerButton.addClickListener(listener -> {
            UI.getCurrent().navigate(UserRegistrationForm.class);
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
