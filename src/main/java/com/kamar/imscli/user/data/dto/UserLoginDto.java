package com.kamar.imscli.user.data.dto;

import jakarta.validation.constraints.Email;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * the login dto.
 * @author kamar baraka.*/

public record UserLoginDto(
        @Email String username,
        String password
) {

    public String getCredentials(){

        String usernamePassword = username + ":" + password;
        return Base64.getEncoder().encodeToString(usernamePassword.getBytes(StandardCharsets.UTF_8));
    }
}
