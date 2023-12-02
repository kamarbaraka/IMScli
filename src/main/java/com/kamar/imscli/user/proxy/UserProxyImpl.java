package com.kamar.imscli.user.proxy;

import com.kamar.imscli.app_props.AppProperties;
import com.kamar.imscli.user.data.dto.UserActivationDto;
import com.kamar.imscli.user.data.dto.UserLoginDto;
import com.kamar.imscli.user.data.dto.UserRegDto;
import com.kamar.imscli.user.exception.UserException;
import com.kamar.imscli.user.model.AppUser;
import com.kamar.imscli.role.model.Role;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * implementation of the user proxy contract.
 * @author kamar baraka.*/

@Service
@Log4j2
@RequiredArgsConstructor
public class UserProxyImpl implements UserProxy{

    private final AppProperties appProperties;
    private final RestTemplate restTemplate;

    @Override
    public AppUser login(UserLoginDto userLoginDto) throws UserException {

        /*prepare login request*/

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(userLoginDto.getCredentials());

        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);
        /*set url*/
        String loginUrl = appProperties.resourceServerBaseUrl() + "/api/v1/login";

        ResponseEntity<AppUser> loginResponse = restTemplate.exchange(loginUrl, HttpMethod.GET,
                requestEntity, AppUser.class);

        if (!loginResponse.getStatusCode().equals(HttpStatus.OK)) {

            /*log and throw*/
            log.warn(loginResponse.getStatusCode());
            throw new UserException("login failed! Check the credentials or activate account and retry");
        }

        return loginResponse.getBody();

    }

    @Override
    public void register(UserRegDto userRegDto) throws UserException {

        /*prepare the request*/

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(appProperties.innitUsername(), appProperties.innitUserPassword());
        headers.setContentType(MediaType.APPLICATION_JSON);

        /*wrap the headers and body*/
        HttpEntity<UserRegDto> requestEntity = new HttpEntity<>(userRegDto, headers);

        /*set the request*/
        String registerUrl = appProperties.resourceServerBaseUrl() + "/api/v1/users/registration/register";

        ResponseEntity<Void> registrationResponse = restTemplate.postForEntity(registerUrl, requestEntity, Void.class);

        if (!registrationResponse.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.warn(registrationResponse.toString());
            log.warn(userRegDto.toString());
            throw new UserException("Registration Failed! Try again.");
        }

    }

    @Override
    public void activate(UserActivationDto userActivationDto) throws UserException {

        /*prepare the request*/

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(appProperties.innitUsername(), appProperties.innitUserPassword());
        headers.setContentType(MediaType.APPLICATION_JSON);

        /*get data*/
        String username = userActivationDto.username();
        String activationToken = userActivationDto.activationToken();

        /*set the url and request entity*/
        String activateUrl = appProperties.resourceServerBaseUrl() + "/api/v1/users/registration/activate?username="+
                username+ "&activation_token=" + activationToken;

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        /*send the request*/
        ResponseEntity<Void> activationResponse = restTemplate.postForEntity(activateUrl, requestEntity, Void.class);

        if (!activationResponse.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.warn(activationResponse.getStatusCode().toString());
            throw new UserException("invalid token! Try again");
        }
    }

    @Override
    public List<Role> getAuthorities() throws UserException {

        /*prepare the request*/

        /*set headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(appProperties.innitUsername(), appProperties.innitUserPassword());

        /*set the url*/
        String allAuthorityUrl = appProperties.resourceServerBaseUrl() + "/api/v1/authority/all";

        /*create the request entity*/
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        ParameterizedTypeReference<List<Role>> authTypeRef = new ParameterizedTypeReference<>() {
        };

        /*send the request*/
        ResponseEntity<List<Role>> allAuthoritiesResponse = restTemplate.exchange(
                allAuthorityUrl, HttpMethod.GET, requestEntity, authTypeRef);


        if (!allAuthoritiesResponse.getStatusCode().equals(HttpStatus.OK)) {
            /*throw*/
            throw new UserException("failed to get the roles.");
        }

        /*return the response*/
        return allAuthoritiesResponse.getBody();
    }

    @Override
    public List<AppUser> getAllUsersWithAuthority(String authority) throws UserException {

        /*get credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        /*set the url*/
        String url = appProperties.resourceServerBaseUrl() + "/api/v1/users/management/authority?authority=" + authority;

        /*set the request*/
        RequestEntity<Object> request = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create(url));

        ParameterizedTypeReference<List<AppUser>> refType = new ParameterizedTypeReference<>(){};
        /*send the request*/
        ResponseEntity<List<AppUser>> response = restTemplate.exchange(request, refType);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.warn(response.getStatusCode());
            throw new UserException("can't reach server");
        }

        return response.getBody();
    }


}
