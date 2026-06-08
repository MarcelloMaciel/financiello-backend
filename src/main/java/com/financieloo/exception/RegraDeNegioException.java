package com.financieloo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraDeNegioException extends RuntimeException {

    public RegraDeNegioException(String mensagem) {
        super(mensagem);
    }
}
