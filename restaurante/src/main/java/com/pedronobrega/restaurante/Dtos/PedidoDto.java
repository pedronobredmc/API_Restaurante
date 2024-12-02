package com.pedronobrega.restaurante.Dtos;

import java.util.List;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class PedidoDto {
    private int numeroMesa;
    private List<String> pizza;
}
