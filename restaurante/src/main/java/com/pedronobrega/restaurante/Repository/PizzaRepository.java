package com.pedronobrega.restaurante.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedronobrega.restaurante.Entities.pizza.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{
    
}