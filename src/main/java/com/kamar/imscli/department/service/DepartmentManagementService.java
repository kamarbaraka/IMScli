package com.kamar.imscli.department.service;

import com.kamar.imscli.department.event.DepartmentCreationEvent;

import java.util.List;

/**
 * department management contract.
 * @author kamar baraka.*/

public interface DepartmentManagementService {

    List<String > getAllDepartmentNames();
    void createDepartment(DepartmentCreationEvent event);
}
