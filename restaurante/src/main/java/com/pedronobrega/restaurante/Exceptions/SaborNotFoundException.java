package com.pedronobrega.restaurante.exceptions;

public class SaborNotFoundException extends RuntimeException {
    public SaborNotFoundException(){
        super();
    }

    public SaborNotFoundException(String message){
        super(message);
    }
}
