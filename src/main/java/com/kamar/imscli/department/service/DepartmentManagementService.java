package com.kamar.imscli.department.service;

import com.kamar.imscli.department.event.DepartmentCreationEvent;
import com.kamar.imscli.department.exception.DepartmentException;

import java.util.List;

/**
 * department management contract.
 * @author kamar baraka.*/

public interface DepartmentManagementService {

    List<String > getAllDepartmentNames();
    void createDepartment(DepartmentCreationEvent event);
    List<String> getEmployeesNotInDept() ;

    void addUserToDept();
}
