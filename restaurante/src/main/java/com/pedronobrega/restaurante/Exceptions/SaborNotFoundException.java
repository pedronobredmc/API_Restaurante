package com.pedronobrega.restaurante.Exceptions;

public class SaborNotFoundException extends RuntimeException {
    public SaborNotFoundException(){
        super();
    }

    public SaborNotFoundException(String message){
        super(message);
    }
}
