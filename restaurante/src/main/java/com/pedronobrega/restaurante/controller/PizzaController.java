package com.pedronobrega.restaurante.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedronobrega.restaurante.Services.PizzaService;
import com.pedronobrega.restaurante.dtos.PizzaDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




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
    public PizzaDto cadastrarPizza(@RequestBody @Valid PizzaDto novaPizzaDto) {
        return pizzaService.cadastrarNovaPizza(novaPizzaDto);   
    }

    @GetMapping("/buscar/{id}")
    public PizzaDto buscarPizzaPorId(@PathVariable @NotNull Long id) {
        return pizzaService.buscarPizzaPorId(id);
    }
    
    @PutMapping("atualizarpizza/{id}")
    public PizzaDto atualizarPizza(@PathVariable @NotNull Long id, @RequestBody @Valid PizzaDto campoParaAtualizar) {
        return pizzaService.atualizarPizza(id, campoParaAtualizar);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarPizza(@PathVariable @NotNull Long id) {
        pizzaService.deletarPizza(id);
    }
}
