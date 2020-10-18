package com.savadev.unrest.aop;

import com.savadev.unrest.domain.config.Config;
import com.savadev.unrest.error.ErrorResponse;
import com.savadev.unrest.error.UnrestException;
import com.savadev.unrest.service.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class RestControllerExceptionAdvice {

    private final ConfigService configService;

    @Autowired
    public RestControllerExceptionAdvice(ConfigService configService) {
        this.configService = configService;
    }

    @ExceptionHandler
    Mono<ResponseEntity<ErrorResponse>> onUnrestException(UnrestException exception) {
        return configService.getConfiguration()
                .map(config -> ResponseEntity.status(exception.getStatus())
                        .body(toResponse(config, exception)));
    }

    @ExceptionHandler
    Mono<ResponseEntity<ErrorResponse>> onException(Exception exception) {
        return configService.getConfiguration()
                .map(config -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(toResponse(config, HttpStatus.INTERNAL_SERVER_ERROR, exception)));
    }

    private ErrorResponse toResponse(Config config, UnrestException exception) {
        return new ErrorResponse(config.getVersion().toString(),
                exception.getStatus().value(),
                exception.getMessage(),
                exception.getErrors()
        );
    }

    private ErrorResponse toResponse(Config config, HttpStatus status, Exception exception) {
        return new ErrorResponse(config.getVersion().toString(),
                status.value(),
                exception.getMessage(),
                null
        );
    }

}
