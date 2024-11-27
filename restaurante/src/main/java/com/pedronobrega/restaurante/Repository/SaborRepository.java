package com.pedronobrega.restaurante.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pedronobrega.restaurante.Entities.pizza.Sabor;

public interface SaborRepository extends JpaRepository<Sabor, Long>{

}
