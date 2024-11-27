package com.pedronobrega.restaurante.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedronobrega.restaurante.Services.PizzaService;
import com.pedronobrega.restaurante.dtos.PizzaDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
//import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping()
    public List<PizzaDto> listarPizzas() {
        return pizzaService.listarPizzas();
    }

    @PostMapping("/cadastrar")
    public PizzaDto cadastrarPizza(@RequestBody PizzaDto novaPizzaDto) {
        return pizzaService.cadastrarNovaPizza(novaPizzaDto);   
    }

    
    
}