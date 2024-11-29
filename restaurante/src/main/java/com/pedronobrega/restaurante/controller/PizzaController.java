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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping()
    public Page<PizzaDto> listarPizzas(@PageableDefault(size = 10) Pageable paginacao) {
        return pizzaService.listarPizzas(paginacao);
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
