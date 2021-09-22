package br.com.password.service;

import br.com.password.model.PasswordResponse;
import reactor.core.publisher.Mono;

public interface ValidatorService {

    Mono<PasswordResponse> lenghtValidator(String password, PasswordResponse passwordResponse);



}
