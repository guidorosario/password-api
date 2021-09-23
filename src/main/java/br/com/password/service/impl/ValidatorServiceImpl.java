package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.Messages.ERROR_DIGIT;
import static br.com.password.constraints.Messages.ERROR_LENGTH;
import static br.com.password.constraints.Messages.ERROR_LOWER_CASE;
import static br.com.password.constraints.Messages.ERROR_SPACE_BETWEEN;
import static br.com.password.constraints.Messages.ERROR_SPECIAL_CHARACTER;
import static br.com.password.constraints.Messages.ERROR_UPPER_CASE;
import static br.com.password.constraints.Messages.ERROR_REPETITION_CHARACTER;
import static br.com.password.constraints.ValidatorConstraints.DIGIT;
import static br.com.password.constraints.ValidatorConstraints.LENGTH;
import static br.com.password.constraints.ValidatorConstraints.LOWER_CASE;
import static br.com.password.constraints.ValidatorConstraints.REPETITION_CHARACTER;
import static br.com.password.constraints.ValidatorConstraints.SPACE_BETWEEN;
import static br.com.password.constraints.ValidatorConstraints.SPECIAL_CHARACTER;
import static br.com.password.constraints.ValidatorConstraints.UPPER_CASE;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(ValidatorService.class);


    @Override
    public Mono<PasswordResponse> lengthValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de tamanho da senha");
        if(!password.matches(LENGTH)) {
            LOG.info("Senha não contem 9 caracteres ou mais");
            passwordResponse.getErrorPassword().add(ERROR_LENGTH);
        }

        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> spaceBetweenValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de espaço na senha");
        if(password.matches(SPACE_BETWEEN)) {
            LOG.info("Senha possui espaços");
            passwordResponse.getErrorPassword().add(ERROR_SPACE_BETWEEN);
        }

        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> digitValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de digito na senha");
        if(!password.matches(DIGIT)) {
            LOG.info("Não possui digito");
            passwordResponse.getErrorPassword().add(ERROR_DIGIT);
        }

        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> lowerCaseValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de letra minuscula na senha");
        if(!password.matches(LOWER_CASE)) {
            LOG.info("Senha nao possui letra minuscula");
            passwordResponse.getErrorPassword().add(ERROR_LOWER_CASE);
        }

        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> upperCaseValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de letra maiúscula na senha");
        if(!password.matches(UPPER_CASE)) {
            LOG.info("Senha nao possui letra maiúscula");
            passwordResponse.getErrorPassword().add(ERROR_UPPER_CASE);
        }

        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> specialCharacterValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de caractere especial na senha");
        if(!password.matches(SPECIAL_CHARACTER)) {
            LOG.info("Senha nao possui caractere especial");
            passwordResponse.getErrorPassword().add(ERROR_SPECIAL_CHARACTER);
        }
        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> repetitionCharacterValidator(String password, PasswordResponse passwordResponse) {
        LOG.info("Iniciando validação de caracteres repetidos");
        if(password.matches(REPETITION_CHARACTER)) {
            LOG.info("Senha possui caracteres repetidos");
            passwordResponse.getErrorPassword().add(ERROR_REPETITION_CHARACTER);
        }
        return Mono.just(passwordResponse);
    }


}
