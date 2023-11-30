package com.kamar.imscli.user.service;

import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

/**
 * the user service contract.
 * @author kamar baraka.*/

public interface UserService {

    void login(TextField usernameField, PasswordField passwordField);

    void register(Select<String > role, TextField usernameField, PasswordField passwordField);

    void activate(TextField activationTokenField);

    List<String > getRoles();
}
