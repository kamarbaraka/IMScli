package com.kamar.imscli.department.service;

import com.kamar.imscli.department.model.Department;
import com.kamar.imscli.department.proxy.DepartmentProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * implementation of the department management contract.
 * @author kamar baraka.*/

@Service
@RequiredArgsConstructor
public class DepartmentManagementServiceImpl implements DepartmentManagementService {

    private final DepartmentProxy departmentProxy;

    @Override
    public List<String> getAllDepartmentNames() {

        /*get the departments*/
        List<Department> allDepartments = departmentProxy.getAllDepartments();

        return allDepartments.stream().map(Department::departmentName).toList();
    }
}
