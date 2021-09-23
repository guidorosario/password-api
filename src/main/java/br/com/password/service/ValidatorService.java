package br.com.password.service;

import br.com.password.model.PasswordResponse;
import reactor.core.publisher.Mono;

public interface ValidatorService {

    Mono<PasswordResponse> lengthValidator(String password, PasswordResponse passwordResponse);

    Mono<PasswordResponse> spaceBetweenValidator (String password, PasswordResponse passwordResponse);

    Mono<PasswordResponse> digitValidator(String password, PasswordResponse passwordResponse);

    Mono<PasswordResponse> lowerCaseValidator(String password, PasswordResponse passwordResponse);

    Mono<PasswordResponse> upperCaseValidator(String password, PasswordResponse passwordResponse);

    Mono<PasswordResponse> specialCharacterValidator(String password, PasswordResponse passwordResponse);

    Mono<PasswordResponse> repetitionCharacterValidator(String password, PasswordResponse passwordResponse);



}
