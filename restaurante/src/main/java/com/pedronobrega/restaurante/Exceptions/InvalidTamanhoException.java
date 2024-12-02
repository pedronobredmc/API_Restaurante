package com.pedronobrega.restaurante.Exceptions;

public class InvalidTamanhoException extends RuntimeException {
    public InvalidTamanhoException(String message) {
        super(message);
    }

    public InvalidTamanhoException() {
        super();
    }

}
