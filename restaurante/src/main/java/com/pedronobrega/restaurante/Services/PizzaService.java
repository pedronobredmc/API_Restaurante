package com.pedronobrega.restaurante.Services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pedronobrega.restaurante.Entities.pizza.Pizza;
import com.pedronobrega.restaurante.Repository.PizzaRepository;
import com.pedronobrega.restaurante.dtos.PizzaDto;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    private final ModelMapper modelMapper;

    public PizzaDto cadastrarNovaPizza(PizzaDto novaPizzaDto){
        Pizza pizza = modelMapper.map(novaPizzaDto, Pizza.class);
        pizzaRepository.save(pizza);
        return modelMapper.map(pizza, PizzaDto.class);
    }

    public List<PizzaDto> listarPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas.stream().map(pizza -> modelMapper.map(pizza, PizzaDto.class)).collect(Collectors.toList());
    }


}
