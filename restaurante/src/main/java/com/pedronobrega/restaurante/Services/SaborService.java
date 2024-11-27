package com.pedronobrega.restaurante.Services;

import org.springframework.stereotype.Service;
import com.pedronobrega.restaurante.Repository.SaborRepository;

import jakarta.validation.constraints.NotNull;

import com.pedronobrega.restaurante.Entities.pizza.Sabor;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaborService {
    private final SaborRepository saborRepository;

    public List<Sabor> listarSabores() {
        return saborRepository.findAll();
    }

    public Sabor cadastrarNovoSabor(Sabor novoSabor) {
        return saborRepository.save(novoSabor);
    }

    public Sabor atualizarSabor(@NotNull Long id, Sabor sabor){
        Sabor saborAtualizado = sabor;
        saborAtualizado.setId(id);
        return saborRepository.save(saborAtualizado);
    }

    public void deletarSabor(@NotNull Long id) {
        saborRepository.deleteById(id);
    }
}
