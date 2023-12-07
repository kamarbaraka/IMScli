package com.kamar.imscli.user.views;

import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

/**
 * the view for user registration.
 * @author kamar baraka.*/

@Route(value = "register")
public class UserRegistrationForm extends VerticalLayout {

    private final UserService userService;

    private final Select<String> roleFiled = new Select<>();
    private final TextField usernameField = new TextField("username");
    private final PasswordField passwordField = new PasswordField("password");

    private final Button loginButton = new Button("login");

    private final Button registerButton = new Button("register");

    public UserRegistrationForm(UserService userService) {
        /*inject the user service*/
        this.userService = userService;

        /*add the components*/
        this.add(getRoleFiled(), getUsernameField(), getPasswordField(), getButtonLayout());

        /*configure this view*/
        this.setAlignItems(Alignment.CENTER);
    }

    private Select<String> getRoleFiled(){

        roleFiled.setRequiredIndicatorVisible(true);
        roleFiled.setMinWidth("500px");
        roleFiled.setErrorMessage("provide the department.");
        roleFiled.setLabel("user role");
        roleFiled.setEmptySelectionCaption("please select a role!");
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
        usernameField.setAutofocus(false);
        usernameField.setRequiredIndicatorVisible(true);
        usernameField.setAutocomplete(Autocomplete.EMAIL);
        usernameField.setAutoselect(true);
        usernameField.setMinWidth("500px");
        usernameField.setErrorMessage("provide your username!");
        usernameField.setPlaceholder("someone@somewhere.com");
        usernameField.setTooltipText("your username");
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
        passwordField.setRequiredIndicatorVisible(true);
        passwordField.setMinWidth("500px");
        passwordField.setErrorMessage("provide your password!");
        passwordField.setPlaceholder("enter password");
        passwordField.setTooltipText("your password");
        passwordField.setValueChangeMode(ValueChangeMode.EAGER);

        passwordField.addValueChangeListener(listener -> {
            registerButton.getStyle().setBackground("green");
            registerButton.getStyle().setColor("white");
            registerButton.setEnabled(true);

        });

        return passwordField;
    }

    private Button getRegisterButton(){

        /*configure the registration button and return*/
        registerButton.setEnabled(false);
        registerButton.setIcon(new Icon(VaadinIcon.USER));
        registerButton.setMinWidth("200px");

        registerButton.addClickListener(listener -> {
            userService.register(this.roleFiled, this.usernameField, this.passwordField);
        });

        return registerButton;
    }

    private Button getLoginButton(){

        /*configure the login button and return*/
        loginButton.setEnabled(true);
        loginButton.getStyle().setBackground("green");
        loginButton.getStyle().setColor("white");
        loginButton.setIcon(new Icon(VaadinIcon.SIGN_IN));
        loginButton.setMinWidth("200px");

        loginButton.addClickListener(listener -> UI.getCurrent().navigate(UserLoginForm.class));

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
