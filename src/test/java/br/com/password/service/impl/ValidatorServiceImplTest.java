package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static br.com.password.constraints.Messages.ERROR_DIGIT;
import static br.com.password.constraints.Messages.ERROR_LENGTH;
import static br.com.password.constraints.Messages.ERROR_LOWER_CASE;
import static br.com.password.constraints.Messages.ERROR_REPETITION_CHARACTER;
import static br.com.password.constraints.Messages.ERROR_SPACE_BETWEEN;
import static br.com.password.constraints.Messages.ERROR_SPECIAL_CHARACTER;
import static br.com.password.constraints.Messages.ERROR_UPPER_CASE;
import static br.com.password.constraints.Password.NO_SPECIAL_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.REPETITION_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.SPACE_BETWEEN_PASSWORD;
import static br.com.password.constraints.Password.NO_DIGIT_PASSWORD;
import static br.com.password.constraints.Password.VALID_PASSWORD;
import static br.com.password.constraints.Password.ONE_CHARACTER_PASSWORD;
import static br.com.password.constraints.Password.NO_LOWER_CASE_PASSWORD;
import static br.com.password.constraints.Password.NO_UPPER_CASE_PASSWORD;
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

    @Test
    @DisplayName("Testando senha > 9 caracteres")
    void lengthPasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.lengthValidator(ONE_CHARACTER_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_LENGTH)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha =< 9 caracteres")
    void lengthPasswordShouldPass() {
        StepVerifier.create(validatorService.lengthValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com espaços")
    void spaceBetweenPasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.spaceBetweenValidator(SPACE_BETWEEN_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_SPACE_BETWEEN)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha valida sem espaço")
    void noSpaceBetweenPasswordShouldPass() {
        StepVerifier.create(validatorService.spaceBetweenValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha sem letra maiuscula")
    void noUpperCasePasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.upperCaseValidator(NO_UPPER_CASE_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_UPPER_CASE)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com letra maiuscula")
    void upperCasePasswordShouldPass() {
        StepVerifier.create(validatorService.upperCaseValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha sem letra minuscula")
    void noLowerCasePasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.lowerCaseValidator(NO_LOWER_CASE_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_LOWER_CASE)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com letra minuscula")
    void lowerCasePasswordShouldPass() {
        StepVerifier.create(validatorService.upperCaseValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }


    @Test
    @DisplayName("Testando senha sem caracteres especial")
    void noSpecialPasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.specialCharacterValidator(NO_SPECIAL_CHARACTER_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_SPECIAL_CHARACTER)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com caracteres especial")
    void specialCasePasswordShouldPass() {
        StepVerifier.create(validatorService.specialCharacterValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha com caracteres repetido")
    void repetitionCharacterPasswordShouldAddErrorPassword() {
        StepVerifier.create(validatorService.repetitionCharacterValidator(REPETITION_CHARACTER_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().contains(ERROR_REPETITION_CHARACTER)))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Testando senha sem caracteres repetido")
    void noRepetitionCharacterPasswordShouldPass() {
        StepVerifier.create(validatorService.repetitionCharacterValidator(VALID_PASSWORD, new PasswordResponse()))
                .expectSubscription()
                .assertNext(response -> assertTrue(response.getErrorPassword().isEmpty()))
                .expectComplete()
                .verify();
    }

}