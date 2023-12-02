package com.kamar.imscli.user.views;

import com.kamar.imscli.user.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * the user activation view.
 * @author kamar baraka.*/

@Route("activate")
public class UserActivationForm extends VerticalLayout {

    private final UserService userService;

    private final TextField activationTokenField = new TextField("activation token");

    private final Button activateButton = new Button("activate");

    public UserActivationForm(UserService userService) {

        /*inject dependencies*/
        this.userService = userService;

        /*set up the view*/
        this.add(getActivationTokenField(), getActivateButton());
        this.setAlignItems(Alignment.CENTER);
    }

    private TextField getActivationTokenField(){
        /*configure the activation token field*/
        activationTokenField.setRequired(true);
        activationTokenField.setRequiredIndicatorVisible(true);
        activationTokenField.setErrorMessage("provide activation token!");
        activationTokenField.setAutofocus(true);
        activationTokenField.setTooltipText("activation token");
        activationTokenField.setPlaceholder("XXXX-XXXXX-XXXX-XXXX");
        activationTokenField.setClearButtonVisible(true);

        activationTokenField.addValueChangeListener(listener -> {
            activateButton.setEnabled(true);
            activateButton.getStyle().setColor("white");
            activateButton.getStyle().setBackground("green");
        });

        return activationTokenField;
    }

    private Button getActivateButton(){

        /*configure the activation button*/
        activateButton.setEnabled(false);

        /*add a listener*/
        activateButton.addClickListener(listener -> {
            userService.activate(this.activationTokenField);
        });

        return activateButton;
    }
}
