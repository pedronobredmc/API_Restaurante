package com.pedronobrega.restaurante.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedronobrega.restaurante.Entities.pizza.Sabor;
import com.pedronobrega.restaurante.Services.SaborService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/sabor")
@RestController
@RequiredArgsConstructor
public class SaborController {
    private final SaborService saborService;

    @GetMapping()
    public List<Sabor> listarSabores() {
        return saborService.listarSabores();
    }

    @PostMapping("/cadastrar")
    public Sabor cadastrarPizza(@RequestBody @Valid Sabor novoSabor) {
        return saborService.cadastrarNovoSabor(novoSabor);   
    }

    @PutMapping("atualizarsabor/{id}")
    public Sabor atualizarSabor(@PathVariable @NotNull Long id, @RequestBody @Valid Sabor campoParaAtualizar) {
        return saborService.atualizarSabor(id, campoParaAtualizar);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarSabor(@PathVariable @NotNull Long id) {
        saborService.deletarSabor(id);
    }
}
