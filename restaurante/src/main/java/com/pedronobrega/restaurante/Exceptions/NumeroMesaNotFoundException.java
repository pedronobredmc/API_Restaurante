package com.pedronobrega.restaurante.Exceptions;

public class NumeroMesaNotFoundException extends RuntimeException {
    public NumeroMesaNotFoundException(){
        super();
    }

    public NumeroMesaNotFoundException(String message){
        super(message);
    }
}
