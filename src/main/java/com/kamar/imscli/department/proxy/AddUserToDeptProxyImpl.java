package com.kamar.imscli.department.proxy;

import com.kamar.imscli.app_props.AppProperties;
import com.kamar.imscli.department.data.AddUsersToDeptDto;
import com.kamar.imscli.department.exception.DepartmentException;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * implementation of add user to dept proxy contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
@Log4j2
public class AddUserToDeptProxyImpl implements AddUserToDeptProxy {

    private final RestTemplate restTemplate;
    private final  AppProperties appProperties;

    @Override
    public void addUsersToDept(AddUsersToDeptDto addUsersToDeptDto) throws DepartmentException {

        /*get the authenticated user credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");

        /*configure headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        /*get data*/
        List<String> employees = addUsersToDeptDto.employees();
        String employeesString = String.join(",", employees);
        String departmentName = addUsersToDeptDto.departmentName();

        /*set the url*/
        String url = appProperties.resourceServerBaseUrl() + "/api/v1/departments/add?users=" + employeesString+
                "&department_name=" + departmentName;

        /*set the request*/
        RequestEntity<Object> request = new RequestEntity<>(null, headers, HttpMethod.POST, URI.create(url));

        /*send the request*/
        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw*/
            log.error(response.getStatusCode());
            throw new DepartmentException("can't reach server");
        }

    }

    @Override
    public List<String> employeesNotInDept() throws DepartmentException {

        /*get the authenticated user credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        /*set the url*/
        URI url = URI.create(appProperties.resourceServerBaseUrl() + "/api/v1/users/report/nonDeptEmps");

        /*set the request*/
        RequestEntity<Object> request = new RequestEntity<>(null, headers, HttpMethod.GET, url);

        /*define the reference type*/
        ParameterizedTypeReference<List<String>> refType = new ParameterizedTypeReference<>(){};

        /*send the request*/
        ResponseEntity<List<String>> response = restTemplate.exchange(request, refType);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and throw */
            log.error(response.getStatusCode());
            throw new DepartmentException("failed to connect to server");
        }

        return response.getBody();
    }


}
