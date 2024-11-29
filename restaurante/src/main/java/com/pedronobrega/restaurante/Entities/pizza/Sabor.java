package com.pedronobrega.restaurante.Entities.pizza;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "sabor")
@Data
@RequiredArgsConstructor
public class Sabor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sabor;

    public Sabor(String sabor) {
        this.sabor = sabor;
    }

}
