package com.kamar.imscli.user.service;

import com.kamar.imscli.ticket.view.TicketCreationView;
import com.kamar.imscli.user.data.dto.UserActivationDto;
import com.kamar.imscli.user.data.dto.UserLoginDto;
import com.kamar.imscli.user.data.dto.UserRegDto;
import com.kamar.imscli.user.exception.UserException;
import com.kamar.imscli.user.model.Authority;
import com.kamar.imscli.user.proxy.UserProxy;
import com.kamar.imscli.user.views.UserActivationView;
import com.kamar.imscli.user.views.UserLoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of the user service contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProxy userProxy;

    @Override
    public void login(TextField usernameField, PasswordField passwordField) {

        /*extract the login data*/
        String username = usernameField.getValue();
        String password = passwordField.getValue();

        /*store the login data*/
        UserLoginDto loginData = new UserLoginDto(username, password);

        try {
            /*send login request*/
            userProxy.login(loginData);
            /*save credentials in session*/
            VaadinSession.getCurrent().setAttribute("authenticatedUser", loginData.getCredentials());
            UserLoginDto ownerCredentials = new UserLoginDto("kamar254baraka@gmail.com", "admin");
            VaadinSession.getCurrent().setAttribute("ownerCredentials", ownerCredentials.getCredentials());
            /*redirect*/
            UI.getCurrent().navigate(TicketCreationView.class);
        } catch (UserException e) {

            /*notify and redirect*/
            Notification.show(e.getMessage());
        }

    }

    @Override
    public void register(Select<String > roleField, TextField usernameField, PasswordField passwordField) {

        /*extract registration data*/
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        String role = roleField.getValue();

        /*store the registration data*/
        UserRegDto userRegDto = new UserRegDto(username, password, role);

        try {
            /*register user*/
            userProxy.register(userRegDto);
            /*redirect to activation*/
            VaadinSession.getCurrent().setAttribute("activationCandidate", username);
            UI.getCurrent().navigate(UserActivationView.class);

        } catch (UserException e) {

            /*notify*/
            Notification.show(e.getMessage());
        }
    }

    @Override
    public void activate(TextField activationTokenField) {

        /*get the data*/
        String username = (String) VaadinSession.getCurrent().getAttribute("activationCandidate");
        String activationToken = activationTokenField.getValue();

        /*store the data*/
        UserActivationDto userActivationDto = new UserActivationDto(username, activationToken);

        try {
            /*activate the user*/
            userProxy.activate(userActivationDto);
            /*redirect to login page*/
            UI.getCurrent().navigate(UserLoginView.class);

        } catch (UserException e) {

            /*notify*/
            Notification.show(e.getMessage());
        }

    }

    @Override
    public List<String> getRoles() {

        try {
            /*get authorities and return*/
            List<Authority> authorities = userProxy.getAuthorities();
            return authorities.stream().map(Authority::authority).toList();
        } catch (UserException e) {

            /*notify*/
            Notification.show(e.getMessage());
            return new ArrayList<>();
        }
    }


}
