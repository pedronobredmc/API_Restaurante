package com.pedronobrega.restaurante.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.pedronobrega.restaurante.Dtos.PedidoDto;
import com.pedronobrega.restaurante.Services.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Page<PedidoDto>> listarPedidos(@PageableDefault(size = 10) Pageable paginacao) {
        Page<PedidoDto> pedidos = pedidoService.listarPedidos(paginacao);
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PedidoDto> cadastrarPedido(@RequestBody @Valid PedidoDto novoPedidoDto, UriComponentsBuilder uriBuilder) {
        PedidoDto novoPedido = pedidoService.cadastrarNovoPedido(novoPedidoDto);
        URI endereco = uriBuilder.path("/pedidos/buscar/{numeroMesa}").buildAndExpand(novoPedido.getNumeroMesa()).toUri();
        return ResponseEntity.created(endereco).body(novoPedido);
    }

    @GetMapping("/buscar/{numeroMesa}")
    public ResponseEntity<PedidoDto> buscarPedidoPorNumeroMesa(@PathVariable @NotNull int numeroMesa) {
        try {
            PedidoDto pedidoBuscado = pedidoService.buscarPedidoPorNumeroMesa(numeroMesa);
            return ResponseEntity.ok(pedidoBuscado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizar/{numeroMesa}")
    public ResponseEntity<PedidoDto> atualizarPedido(@PathVariable @NotNull int numeroMesa, @RequestBody @Valid PedidoDto camposParaAtualizar) {
        try {
            PedidoDto pedidoAtualizado = pedidoService.atualizarPedido(numeroMesa, camposParaAtualizar);
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{numeroMesa}")
    public ResponseEntity<Void> deletarPedido(@PathVariable @NotNull int numeroMesa) {
        try {
            pedidoService.deletarPedido(numeroMesa);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
