package com.kamar.imscli.department.data;

import java.util.List;

/**
 * dto to add employees to a department.
 * @author kamar baraka.*/

public record AddUsersToDeptDto(
        List<String > employees,
        String departmentName

) {
}
