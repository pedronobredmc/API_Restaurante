package com.pedronobrega.restaurante.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pedronobrega.restaurante.Entities.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumeroMesa(int numeroMesa);

    boolean existsByNumeroMesa(int numeroMesa);

}
