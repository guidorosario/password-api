package br.com.password.model.request;

import javax.validation.constraints.NotBlank;

public record PasswordRequest (@NotBlank() String password) {

}
