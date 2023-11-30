package com.kamar.imscli.user.model;

import jakarta.validation.constraints.Email;

import java.util.List;

/**
 * the user model class.
 * @author kamar baraka.*/

public record AppUser(
        @Email String username,
        List<String > authorities,
        int rating
) {
}
