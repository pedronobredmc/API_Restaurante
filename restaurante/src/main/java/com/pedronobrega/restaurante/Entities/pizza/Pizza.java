package com.pedronobrega.restaurante.Entities.pizza;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "pizzas")
@Data
@RequiredArgsConstructor
public class Pizza {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double preco;
    private boolean disponivel;
    private String sabor;
    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Pizza(String sabor, Double preco, boolean disponivel, Tamanho tamanho, Categoria categoria) {
        this.sabor = sabor;
        this.preco = preco;
        this.disponivel = disponivel;
        this.tamanho = tamanho;
        this.categoria = categoria;
    }
}

