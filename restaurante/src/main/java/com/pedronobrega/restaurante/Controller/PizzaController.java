package com.pedronobrega.restaurante.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pedronobrega.restaurante.Services.PizzaService;
import com.pedronobrega.restaurante.dtos.PizzaDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping()
    public ResponseEntity<Page<PizzaDto>> listarPizzas(@PageableDefault(size = 10) Pageable paginacao) {
        Page<PizzaDto> pizzas = pizzaService.listarPizzas(paginacao);
        return ResponseEntity.ok(pizzas);

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PizzaDto> cadastrarPizza(@RequestBody @Valid PizzaDto novaPizzaDto, UriComponentsBuilder uriBuilder) {
        PizzaDto novaPizza =  pizzaService.cadastrarNovaPizza(novaPizzaDto);
        URI endereco = uriBuilder.path("/pizzas/buscar/{id}").buildAndExpand(novaPizza.getId()).toUri();
        return ResponseEntity.created(endereco).body(novaPizza);   
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<PizzaDto> buscarPizzaPorId(@PathVariable @NotNull Long id) {
        PizzaDto pizzaBuscada = pizzaService.buscarPizzaPorId(id);
        return ResponseEntity.ok(pizzaBuscada);
    }
    
    @PutMapping("atualizarpizza/{id}")
    public ResponseEntity<PizzaDto> atualizarPizza(@PathVariable @NotNull Long id, @RequestBody @Valid PizzaDto campoParaAtualizar) {
        PizzaDto pizzaAtualizada = pizzaService.atualizarPizza(id, campoParaAtualizar);
        return ResponseEntity.ok(pizzaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarPizza(@PathVariable @NotNull Long id) {
        pizzaService.deletarPizza(id);
        return ResponseEntity.noContent().build();
    }
}
