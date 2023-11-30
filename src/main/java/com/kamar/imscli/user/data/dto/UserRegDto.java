package com.kamar.imscli.user.data.dto;

import jakarta.validation.constraints.Email;

/**
 * the user registration dto.
 * @author kamar baraka.*/

public record UserRegDto(
        @Email String username,
        String password,
        String role
) {
}
