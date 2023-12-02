package com.kamar.imscli.department.proxy;

import com.kamar.imscli.app_props.AppProperties;
import com.kamar.imscli.department.data.DepartmentCreationDto;
import com.kamar.imscli.department.exception.DepartmentException;
import com.kamar.imscli.department.model.Department;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * implementation of the department proxy contract.
 * @author kamar baraka.*/


@Service
@Log4j2
@RequiredArgsConstructor
public class DepartmentProxyImpl implements DepartmentProxy {

    private final RestTemplate restTemplate;
    private final AppProperties appProperties;

    @Override
    public void createDepartment(DepartmentCreationDto departmentCreationDto) throws DepartmentException {

        /*get authenticated credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("authenticatedUser");
        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        /*get data to send*/
        String departmentName = departmentCreationDto.DepartmentName();
        String email = departmentCreationDto.email();
        String headOfDepartment = departmentCreationDto.headOfDepartment();

        /*set the body*/
        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        /*set the url*/
        String requestUrl = appProperties.resourceServerBaseUrl() + "/api/v1/departments?department_name=" + departmentName +
                "&email=" + email + "&head_of_department=" + headOfDepartment;

        /*send the request*/
        ResponseEntity<Void> response = restTemplate.postForEntity(requestUrl, request, Void.class);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log and respond*/
            log.error(response.getStatusCode());
            throw new DepartmentException("can't reach server");
        }
    }

    @Override
    public List<Department> getAllDepartments() throws DepartmentException{

        /*get the credentials*/
        String credentials = (String) VaadinSession.getCurrent().getAttribute("ownerCredentials");

        /*set the headers*/
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(credentials);

        /*set the request*/
        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        /*set the url*/
        String requestUrl = appProperties.resourceServerBaseUrl() + "/api/v1/departments/all";

        ParameterizedTypeReference<List<Department>> deptTypeRef = new ParameterizedTypeReference<>() {
        };

        /*send the request*/
        ResponseEntity<List<Department>> response = restTemplate.exchange(requestUrl, HttpMethod.GET, request, deptTypeRef);

        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            /*log*/
            log.warn(response.getStatusCode());
            throw new DepartmentException("can't reach server");
        }

        return response.getBody();
    }

}
