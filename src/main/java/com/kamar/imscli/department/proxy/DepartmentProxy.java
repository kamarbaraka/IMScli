package com.kamar.imscli.department.proxy;

import com.kamar.imscli.department.model.Department;

import java.util.List;

/**
 * proxy contract for department.
 * @author kamar baraka.*/

public interface DepartmentProxy {

    List<Department> getAllDepartments();

}
