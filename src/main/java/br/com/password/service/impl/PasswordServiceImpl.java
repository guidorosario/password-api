package br.com.password.service.impl;

import br.com.password.model.PasswordResponse;
import br.com.password.model.request.PasswordRequest;
import br.com.password.service.PasswordService;
import br.com.password.service.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PasswordServiceImpl implements PasswordService {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordServiceImpl.class);

    private final ValidatorService validatorService;

    public PasswordServiceImpl(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    @Override
    public Mono<PasswordResponse> validatorPassword(PasswordRequest request) {

        return validatorService.lengthValidator(request.password(), new PasswordResponse())
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de tamanho finalizada"))
                .flatMap(passwordResponse ->  validatorService.spaceBetweenValidator(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de espacos finalizada"))
                .flatMap(passwordResponse -> validatorService.digitValidator(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de digito finalizada"))
                .flatMap(passwordResponse -> validatorService.lowerCaseValidator(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de letra minuscula finalizada"))
                .flatMap(passwordResponse -> validatorService.upperCaseValidator(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de maiuscula finalizada"))
                .flatMap(passwordResponse -> validatorService.specialCharacterValidator(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de caractere especial finalizada"))
                .flatMap(passwordResponse -> validatorService.repetitionCharacterValidator(request.password(), passwordResponse))
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de caractere repetido finalizada"))
                .map(PasswordResponse::checkResponse);
    }
}
