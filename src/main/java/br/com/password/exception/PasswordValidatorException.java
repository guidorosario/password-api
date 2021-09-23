package br.com.password.exception;

import org.apache.http.HttpStatus;

import static br.com.password.constraints.Messages.INVALID_PASSWORD;

public class PasswordValidatorException extends  PasswordException {

    public PasswordValidatorException(String description) {
        super(HttpStatus.SC_BAD_REQUEST, description, INVALID_PASSWORD);
    }
}
