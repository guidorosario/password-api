package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_DIGIT;
import static br.com.password.constraints.Messages.ERROR_LOWER_CASE;
import static br.com.password.constraints.Password.NO_DIGIT_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorServiceImplTest {

    @InjectMocks ValidatorServiceImpl validatorService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testando senha sem digito")
    void notDigitPasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.digitValidator(NO_DIGIT_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_DIGIT)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com digito")
    void DigitPasswordShouldPass() {
        StepVerifier.create(validatorService.digitValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }
}