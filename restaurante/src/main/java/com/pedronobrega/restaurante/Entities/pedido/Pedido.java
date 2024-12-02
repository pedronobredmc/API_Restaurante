package com.pedronobrega.restaurante.Entities.pedido;

import com.pedronobrega.restaurante.Entities.pizza.Pizza;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "pedidos")
@Data
@RequiredArgsConstructor
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Pizza> pizza;
    @Enumerated (EnumType.STRING)
    private Andamento andamento;
    private int numeroMesa;

    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.pizza =  new ArrayList<Pizza>();
    }
}
