package com.pedronobrega.restaurante.Entities.pizza;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String nome;
    private Double preco;
    private boolean disponivel;
    @ManyToOne
    @JoinColumn(name = "sabor_id")
    private Sabor sabor;
    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public Pizza(String nome, Double preco, boolean disponivel, Tamanho tamanho, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.disponivel = disponivel;
        this.tamanho = tamanho;
        this.categoria = categoria;
    }
}

