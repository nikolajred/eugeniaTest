package com.nix.eugenia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


public class ClientRequestException  extends ResponseStatusException {
    public ClientRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public ClientRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }


}
