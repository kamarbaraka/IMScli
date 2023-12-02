package com.kamar.imscli.department.proxy;

import com.kamar.imscli.department.data.DepartmentCreationDto;
import com.kamar.imscli.department.exception.DepartmentException;
import com.kamar.imscli.department.model.Department;

import java.util.List;

/**
 * proxy contract for department.
 * @author kamar baraka.*/

public interface DepartmentProxy {

    List<Department> getAllDepartments() throws DepartmentException;

    void createDepartment(DepartmentCreationDto departmentCreationDto) throws DepartmentException;

}
