package com.pedronobrega.restaurante.Dtos;

import com.pedronobrega.restaurante.Entities.pizza.Categoria;
import com.pedronobrega.restaurante.Entities.pizza.Tamanho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PizzaDto {
    private Long id;
    @Positive
    private Double preco;
    private boolean disponivel;
    private Tamanho tamanho;
    @NotNull
    @NotBlank
    private String sabor;
    private Categoria categoria;
}
