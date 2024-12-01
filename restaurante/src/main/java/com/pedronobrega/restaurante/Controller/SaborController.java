package com.pedronobrega.restaurante.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pedronobrega.restaurante.Entities.pizza.Sabor;
import com.pedronobrega.restaurante.Services.SaborService;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RequestMapping("/sabor")
@RestController
@RequiredArgsConstructor
public class SaborController {
    @Autowired
    private final SaborService saborService;

    @GetMapping()
    public ResponseEntity<List<Sabor>> listarSabores() {
        List<Sabor> sabores = saborService.listarSabores();
        return ResponseEntity.ok(sabores);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Sabor> cadastrarSabor(@RequestBody @Valid Sabor novoSabor, UriComponentsBuilder uriBuilder) {
        Sabor saborCadastrado = saborService.cadastrarNovoSabor(novoSabor);
        URI endereco = uriBuilder.path("/sabor/buscar/{id}").buildAndExpand(saborCadastrado.getId()).toUri();
        return ResponseEntity.created(endereco).body(saborCadastrado);
    }

    @PutMapping("atualizarsabor/{id}")
    public ResponseEntity<Sabor> atualizarSabor(@PathVariable @NotNull Long id, @RequestBody @Valid Sabor campoParaAtualizar) {
        Sabor saborAtualizado = saborService.atualizarSabor(id, campoParaAtualizar);
        return ResponseEntity.ok(saborAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSabor(@PathVariable @NotNull Long id) {
        saborService.deletarSabor(id);
        return ResponseEntity.noContent().build();
    }
}
