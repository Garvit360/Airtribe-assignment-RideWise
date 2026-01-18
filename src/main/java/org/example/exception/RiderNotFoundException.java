package org.example.exception;

public class RiderNotFoundException extends RuntimeException{
    public RiderNotFoundException(String message){
        super(message);
    }
}
