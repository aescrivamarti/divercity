package com.example.demo.repository;

import com.example.demo.model.Reserva;
import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Listar todas las reservas de un cliente
    List<Reserva> findByCliente(Cliente cliente);

    // Buscar reservas por fecha
    List<Reserva> findByFecha(java.time.LocalDate fecha);
}
