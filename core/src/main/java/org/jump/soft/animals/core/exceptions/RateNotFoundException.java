package org.jump.soft.animals.core.exceptions;

public class RateNotFoundException extends RuntimeException {

    public RateNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}