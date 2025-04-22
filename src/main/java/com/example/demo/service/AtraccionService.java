package com.example.demo.service;

import com.example.demo.model.Atraccion;
import com.example.demo.repository.AtraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtraccionService {

    @Autowired
    private AtraccionRepository atraccionRepository;

    public List<Atraccion> listarAtracciones() {
        return atraccionRepository.findAll();
    }

    public Optional<Atraccion> buscarPorId(Long id) {
        return atraccionRepository.findById(id);
    }

    public Atraccion guardarAtraccion(Atraccion atraccion) {
        return atraccionRepository.save(atraccion);
    }

    public void eliminarAtraccion(Long id) {
        atraccionRepository.deleteById(id);
    }
}