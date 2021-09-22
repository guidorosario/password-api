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

        return validatorService.lenghtValidator(request.password(), new PasswordResponse())
                .doOnSuccess(passwordResponse -> LOG.info("Validacao de tamanho finalizada, iniciando processo de validacao de espacos"))  ;
    }
}
