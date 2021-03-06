package com.cogoun.streaming.collaboration.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CollaborationCannotBeStartedException extends RuntimeException {

    public CollaborationCannotBeStartedException(Throwable cause) {
        super(cause);
    }
}
