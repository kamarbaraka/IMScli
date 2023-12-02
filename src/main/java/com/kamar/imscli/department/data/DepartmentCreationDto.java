package com.kamar.imscli.department.data;

import jakarta.validation.constraints.Email;

/**
 * the department creation dto.
 * @author kamar baraka.*/

public record DepartmentCreationDto(
        String DepartmentName,
        @Email String email,
        @Email String headOfDepartment
) {
}
