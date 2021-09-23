package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static br.com.password.constraints.ValidatorConstraints.LENGTH;
import static br.com.password.constraints.ValidatorConstraints.SPACE_BETWEEN;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    private static final Logger LOG = LoggerFactory.getLogger(ValidatorService.class);


    @Override
    public Mono<PasswordResponse> lenghtValidator(String password, PasswordResponse passwordResponse) {
        if(!password.matches(LENGTH)) {
            LOG.info("Senha não contem 9 caracteres ou mais");
            passwordResponse.getErrorPassword().add("Senha deve ter no minimo 9 caracteres");
        }

        return Mono.just(passwordResponse);
    }

    @Override
    public Mono<PasswordResponse> spaceBetweenValidator(String password, PasswordResponse passwordResponse) {
        if(password.matches(SPACE_BETWEEN)) {
            LOG.info("Senha possui espaços");
            passwordResponse.getErrorPassword().add("Senha não deve conter espaços");
        }

        return Mono.just(passwordResponse);
    }


}
