package de.hey_car.exception;

public class NotDataFoundException extends RuntimeException {
    public NotDataFoundException() {
        super();
    }

    public NotDataFoundException(String errorMessage) {
        super(errorMessage);
    }
}
