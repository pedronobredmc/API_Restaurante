package com.pedronobrega.restaurante.Entities.pizza;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sabor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sabor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Sabor;
}
