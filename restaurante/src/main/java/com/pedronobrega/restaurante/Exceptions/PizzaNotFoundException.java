package com.pedronobrega.restaurante.Exceptions;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(String message) {
        super(message);
    }

    public PizzaNotFoundException() {
        super();
    }

}
