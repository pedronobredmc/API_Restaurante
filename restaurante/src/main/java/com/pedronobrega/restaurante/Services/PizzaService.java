package com.pedronobrega.restaurante.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pedronobrega.restaurante.Dtos.PizzaDto;
import com.pedronobrega.restaurante.Entities.pizza.Pizza;
import com.pedronobrega.restaurante.Exceptions.IdNotFoundException;
import com.pedronobrega.restaurante.Repository.PizzaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PizzaDto cadastrarNovaPizza(PizzaDto novaPizzaDto) {
        Pizza novaPizza = new Pizza(novaPizzaDto.getSabor(), novaPizzaDto.getPreco(), novaPizzaDto.isDisponivel(), novaPizzaDto.getTamanho(), novaPizzaDto.getCategoria());
        Pizza pizzaSalva = pizzaRepository.save(novaPizza);
        return modelMapper.map(pizzaSalva, PizzaDto.class);
    }

    public Page<PizzaDto> listarPizzas(Pageable paginacao){
        return pizzaRepository.findAll(paginacao).map(p -> modelMapper.map(p, PizzaDto.class)); 
    }

    public PizzaDto buscarPizzaPorId(Long Id){
        if (!pizzaRepository.existsById(Id)){
            throw new IdNotFoundException("Id não encontrado");
        }else{
            return modelMapper.map(pizzaRepository.findById(Id), PizzaDto.class);
        }
    }

    public PizzaDto atualizarPizza(Long id, PizzaDto camposParaAtualizar){
        Pizza pizza = modelMapper.map(camposParaAtualizar, Pizza.class);
        if(!pizzaRepository.existsById(id)){
            throw new IdNotFoundException("Id não encontrado");
        }else{
            pizza.setId(id);
        }
        pizzaRepository.save(pizza);
        return modelMapper.map(pizza, PizzaDto.class);
    }

    public void deletarPizza(Long id){
        if(!pizzaRepository.existsById(id)){
            throw new IdNotFoundException("Id não encontrado");
        }else{
            pizzaRepository.deleteById(id);
        }
    }
}
