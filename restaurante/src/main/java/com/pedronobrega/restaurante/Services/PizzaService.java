package com.pedronobrega.restaurante.Services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedronobrega.restaurante.Entities.pizza.Pizza;
import com.pedronobrega.restaurante.Entities.pizza.Sabor;
import com.pedronobrega.restaurante.Repository.PizzaRepository;
import com.pedronobrega.restaurante.Repository.SaborRepository;
import com.pedronobrega.restaurante.dtos.PizzaDto;
import com.pedronobrega.restaurante.exceptions.SaborNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final ModelMapper modelMapper;
    private final SaborRepository saborRepository;

    public PizzaDto cadastrarNovaPizza(PizzaDto novaPizzaDto) {
        Pizza novaPizza = new Pizza(novaPizzaDto.getNome(), novaPizzaDto.getPreco(), novaPizzaDto.isDisponivel(), novaPizzaDto.getTamanho(), novaPizzaDto.getCategoria());
        Sabor novoSabor = new Sabor(novaPizzaDto.getSabor());
        boolean saborEncontrado = false;

        for (Sabor sabor : saborRepository.findAll()) {
            if (sabor.getSabor().equals(novoSabor.getSabor())) {
                novoSabor = sabor;
                saborEncontrado = true;
                break;
            }
        }
        if (!saborEncontrado) {
            // Lançar exceção ou retornar erro
            throw new SaborNotFoundException("Sabor não encontrado");
        }
        novaPizza.setSabor(novoSabor);
        Pizza pizzaSalva = pizzaRepository.save(novaPizza);
        return modelMapper.map(pizzaSalva, PizzaDto.class);
    }

    public Page<PizzaDto> listarPizzas(Pageable paginacao){
        return pizzaRepository.findAll(paginacao).map(p -> modelMapper.map(p, PizzaDto.class)); 
    }

    public PizzaDto buscarPizzaPorId(Long Id){
        Pizza pizza = pizzaRepository.findById(Id).orElseThrow();
        return modelMapper.map(pizza, PizzaDto.class);
    }

    public PizzaDto atualizarPizza(Long id, PizzaDto camposParaAtualizar){
        Pizza pizza = modelMapper.map(camposParaAtualizar, Pizza.class);
        pizza.setId(id);
        pizzaRepository.save(pizza);
        return modelMapper.map(pizza, PizzaDto.class);
    }

    public void deletarPizza(Long id){
        pizzaRepository.deleteById(id);
    }
}
