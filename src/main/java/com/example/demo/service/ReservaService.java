package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Reserva;
import com.example.demo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> listarPorCliente(Cliente cliente) {
        return reservaRepository.findByCliente(cliente);
    }

    public List<Reserva> listarPorFecha(LocalDate fecha) {
        return reservaRepository.findByFecha(fecha);
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva guardarReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }
}