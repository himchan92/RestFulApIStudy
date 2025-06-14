package org.zerock.ex1.exception;

public class EntityNotFoundException extends RuntimeException {

    private String message;
    private int code;

    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
        this.code = 404;
    }
}
