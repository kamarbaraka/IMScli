package com.kamar.imscli.role.proxy;

import com.kamar.imscli.app_props.AppProperties;
import com.kamar.imscli.role.exception.RoleException;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * implementation of the role creation proxy contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleCreationProxyImpl implements RoleCreationProxy {

    private final RestTemplate restTemplate;
    private final AppProperties appProperties;

    @Override
    public void createRole(String authority) throws RoleException {

        /*get the authenticated user credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        /*set the url*/
        String url = appProperties.resourceServerBaseUrl() + "/api/v1/authority?role_name=" + authority;

        /*set the request*/
        RequestEntity<Object> request = new RequestEntity<>(null, headers, HttpMethod.POST, URI.create(url));

        /*send the request*/
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.error(response.getStatusCode());
            throw new RoleException("can't reach server");
        }

    }
}
