package com.kamar.imscli.user.exception;

import java.io.IOException;

/**
 * representation of a user exception.
 * @author kamar baraka.*/

public class UserException extends IOException {

    public UserException(String message) {
        super(message);
    }
}
