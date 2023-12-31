package com.kamar.imscli.department.service;

import com.kamar.imscli.department.data.DepartmentCreationDto;
import com.kamar.imscli.department.event.DepartmentCreationEvent;
import com.kamar.imscli.department.exception.DepartmentException;
import com.kamar.imscli.department.model.Department;
import com.kamar.imscli.department.proxy.AddUserToDeptProxy;
import com.kamar.imscli.department.proxy.DepartmentProxy;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of the department management contract.
 * @author kamar baraka.*/

@Service
@Log4j2
@RequiredArgsConstructor
public class DepartmentManagementServiceImpl implements DepartmentManagementService {

    private final DepartmentProxy departmentProxy;
    private final AddUserToDeptProxy addUserToDeptProxy;

    @Override
    public List<String> getAllDepartmentNames() {

        try
        {
            /*get the departments*/
            List<Department> allDepartments = departmentProxy.getAllDepartments();

            return allDepartments.stream().map(Department::departmentName).toList();
        }catch (DepartmentException e){
            /*notify*/
            Notification.show(e.getMessage());
            return new ArrayList<>();
        }
    }

    @EventListener(classes = {DepartmentCreationEvent.class})
    @Override
    public void createDepartment(DepartmentCreationEvent event) {

        /*get the data*/
        String departmentName = event.getDepartmentNameField().getValue();
        String email = event.getEmailField().getValue();
        String headOfDepartment = event.getHeadOfDepartmentField().getValue();

        /*store the data*/
        DepartmentCreationDto creationDto = new DepartmentCreationDto(departmentName, email, headOfDepartment);

        /*create*/
        try {
            /*create department*/
            departmentProxy.createDepartment(creationDto);

            /*redirect*/
            UI.getCurrent().navigate("");

        } catch (DepartmentException e) {
            /*notify*/
            Notification.show(e.getMessage());
        }
    }

    @Override
    public List<String> getEmployeesNotInDept() {

        /*get the employees*/
        try {
            return addUserToDeptProxy.employeesNotInDept();
        } catch (DepartmentException e) {

            /*log */
            log.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void addUserToDept() {

        /*create the event*/
    }
}
