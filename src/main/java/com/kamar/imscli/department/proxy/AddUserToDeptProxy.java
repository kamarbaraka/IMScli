package com.kamar.imscli.department.proxy;

import com.kamar.imscli.department.data.AddUsersToDeptDto;
import com.kamar.imscli.department.exception.DepartmentException;

/**
 * contract to add users to department.
 * @author kamar baraka.*/

public interface AddUserToDeptProxy {

    void addUsersToDept(AddUsersToDeptDto addUsersToDeptDto) throws DepartmentException;
}
