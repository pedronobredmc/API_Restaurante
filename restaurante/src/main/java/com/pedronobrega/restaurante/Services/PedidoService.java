package com.pedronobrega.restaurante.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedronobrega.restaurante.Repository.PedidoRepository;
import com.pedronobrega.restaurante.Repository.PizzaRepository;
import com.pedronobrega.restaurante.Dtos.PedidoDto;
import com.pedronobrega.restaurante.Entities.pedido.Pedido;
import com.pedronobrega.restaurante.Entities.pizza.Pizza;
import com.pedronobrega.restaurante.Entities.pizza.Tamanho;

import static com.pedronobrega.restaurante.Entities.pedido.Andamento.INICIADO;

import java.util.List;

import com.pedronobrega.restaurante.Exceptions.NumeroMesaNotFoundException;
import com.pedronobrega.restaurante.Exceptions.SaborNotFoundException;
import com.pedronobrega.restaurante.Exceptions.PizzaNotFoundException;
import com.pedronobrega.restaurante.Exceptions.InvalidTamanhoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PizzaRepository pizzaRepository;

    public PedidoDto cadastrarNovoPedido(PedidoDto novoPedidoDto) {
        //TODO: Verificar se existe pedido em aberto para a mesa
        Pedido novoPedido = new Pedido(novoPedidoDto.getNumeroMesa());
        List<String> tamanhosString = novoPedidoDto.getTamanho();
        List<String> pizzasString = novoPedidoDto.getPizza();
        if (tamanhosString.size() != pizzasString.size()) {
            throw new IllegalArgumentException("O número de tamanhos não corresponde ao número de pizzas.");
        }
        for (int i = 0; i < pizzasString.size(); i++) {
            String sabor = pizzasString.get(i);
            String tamanhoStr = tamanhosString.get(i);

            if (!pizzaRepository.existsBySabor(sabor)) {
                throw new SaborNotFoundException("Sabor não encontrado: " + sabor);
            }
            Tamanho tamanho;
            try {
                tamanho = Tamanho.valueOf(tamanhoStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidTamanhoException("Tamanho inválido: " + tamanhoStr);
            }

            Pizza pizza = pizzaRepository.findBySaborAndTamanho(sabor, tamanho);
            
            if (pizza != null) {
                novoPedido.getPizza().add(pizza);
            } else {
                throw new PizzaNotFoundException("Pizza com sabor " + sabor + " e tamanho " + tamanho + " não encontrada.");
            }
        }

        novoPedido.setAndamento(INICIADO);
        Pedido pedidoSalvo = pedidoRepository.save(novoPedido);
        return modelMapper.map(pedidoSalvo, PedidoDto.class);
    }

    public Page<PedidoDto> listarPedidos(Pageable paginacao) {
        return pedidoRepository.findAll(paginacao).map(p -> modelMapper.map(p, PedidoDto.class));
    }

    public PedidoDto buscarPedidoPorNumeroMesa(int numeroMesa) {
        Pedido pedido = pedidoRepository.findByNumeroMesa(numeroMesa)
            .orElseThrow(() -> new NumeroMesaNotFoundException("Numero da mesa não encontrado"));
        return modelMapper.map(pedido, PedidoDto.class);
    }

    public PedidoDto atualizarPedido(int numeroMesa, PedidoDto camposParaAtualizar) {
        Pedido pedidoExistente = pedidoRepository.findByNumeroMesa(numeroMesa)
            .orElseThrow(() -> new NumeroMesaNotFoundException("Numero da mesa não encontrado"));

        Pedido pedidoAtualizado = modelMapper.map(camposParaAtualizar, Pedido.class);
        pedidoAtualizado.setNumeroMesa(numeroMesa); // Mantém o número da mesa
        pedidoAtualizado.setId(pedidoExistente.getId()); // Garante que o ID original seja usado
        Pedido pedidoSalvo = pedidoRepository.save(pedidoAtualizado);

        return modelMapper.map(pedidoSalvo, PedidoDto.class);
    }

    public void deletarPedido(int numeroMesa) {
        Pedido pedido = pedidoRepository.findByNumeroMesa(numeroMesa)
            .orElseThrow(() -> new NumeroMesaNotFoundException("Numero da mesa não encontrado"));
        pedidoRepository.deleteById(pedido.getId());
    }
}

