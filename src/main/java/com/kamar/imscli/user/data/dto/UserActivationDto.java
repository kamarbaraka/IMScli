package com.kamar.imscli.user.data.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.UUID;

/**
 * the user activation dto.
 * @author kamar baraka.*/

public record UserActivationDto(

        @Email String username,
        @UUID String activationToken
) {
}
