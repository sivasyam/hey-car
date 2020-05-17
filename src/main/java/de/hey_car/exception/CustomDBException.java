package de.hey_car.exception;

public class CustomDBException extends RuntimeException {
    public CustomDBException(){
        super();
    }
    public CustomDBException(String errorMessage){
        super(errorMessage);
    }
}
