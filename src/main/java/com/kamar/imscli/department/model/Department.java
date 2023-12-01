package com.kamar.imscli.department.model;

import java.util.List;

/**
 * the department model.
 * @author kamar baraka.*/

public record Department(
        String departmentName,
        String email,
        String headOfDepartment,
        int rating,
        List<String > members
) {
}
