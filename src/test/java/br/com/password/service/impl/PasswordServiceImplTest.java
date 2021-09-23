package br.com.password.service.impl;

import br.com.password.exception.PasswordValidatorException;
import br.com.password.mock.MockPasswordResponse;
import br.com.password.model.PasswordResponse;
import br.com.password.model.request.PasswordRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import br.com.password.mock.MockPasswordResponse.*;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_LENGTH;
import static br.com.password.constraints.Password.EMPTY_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static br.com.password.mock.MockPasswordResponse.passwordResponseAllErrors;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class PasswordServiceImplTest {

    @Mock private ValidatorServiceImpl validatorService;

    @InjectMocks private PasswordServiceImpl passwordService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando uma senha valida")
    void validPasswordShouldPass() {

        var request = new PasswordRequest(VALID_PASSWORD);


        when(validatorService.lengthValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.spaceBetweenValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.digitValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.upperCaseValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.lowerCaseValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.specialCharacterValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.repetitionCharacterValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        StepVerifier.create(passwordService.validatorPassword(request))
                .assertNext(response -> assertTrue(response.getCheckedPassword())).verifyComplete();
    }

    @Test
    @DisplayName("Testando uma senha vazia")
    void emptyPasswordShouldError() {

        var request = new PasswordRequest(EMPTY_PASSWORD);

        when(validatorService.lengthValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.spaceBetweenValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.digitValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.upperCaseValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.lowerCaseValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.specialCharacterValidator(any(), any())).thenReturn(Mono.just(new PasswordResponse()));

        when(validatorService.repetitionCharacterValidator(any(), any())).thenReturn(Mono.just(passwordResponseAllErrors()));

        StepVerifier.create(passwordService.validatorPassword(request))
                .expectSubscription()
                .expectError(PasswordValidatorException.class).verify();
    }

}