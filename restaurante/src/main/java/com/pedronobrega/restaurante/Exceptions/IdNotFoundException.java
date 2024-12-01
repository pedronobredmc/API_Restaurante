package com.pedronobrega.restaurante.Exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(){
        super();
    }

    public IdNotFoundException(String message){
        super(message);
    }
}
