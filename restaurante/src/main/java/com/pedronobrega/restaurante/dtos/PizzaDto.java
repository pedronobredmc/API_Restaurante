package com.pedronobrega.restaurante.dtos;

import com.pedronobrega.restaurante.Entities.pizza.Sabor;
import com.pedronobrega.restaurante.Entities.pizza.Tamanho;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaDto {
    private String nome;
    private Double preco;
    private boolean disponivel;
    private Tamanho tamanho;
    private Sabor sabor;
}
