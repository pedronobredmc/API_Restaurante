package com.pedronobrega.restaurante.dtos;

import com.pedronobrega.restaurante.Entities.pizza.Categoria;
import com.pedronobrega.restaurante.Entities.pizza.Tamanho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaDto {
    @NotNull
    @NotBlank
    private String nome;
    @Positive
    private Double preco;
    private boolean disponivel;
    private Tamanho tamanho;
    private String sabor;
    private Categoria categoria;
}
