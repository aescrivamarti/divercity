package com.example.demo.controller;

import com.example.demo.model.Atraccion;
import com.example.demo.service.AtraccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/atracciones")
public class AtraccionController {

    @Autowired
    private AtraccionService atraccionService;

    // Listar todas las atracciones
    @GetMapping
    public List<Atraccion> listarAtracciones() {
        return atraccionService.listarAtracciones();
    }

    // Buscar atracción por ID
    @GetMapping("/{id}")
    public ResponseEntity<Atraccion> obtenerAtraccion(@PathVariable Long id) {
        Optional<Atraccion> atraccion = atraccionService.buscarPorId(id);
        return atraccion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nueva atracción
    @PostMapping
    public ResponseEntity<Atraccion> crearAtraccion(@RequestBody Atraccion atraccion) {
        Atraccion nuevaAtraccion = atraccionService.guardarAtraccion(atraccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAtraccion);
    }

    // Actualizar atracción
    @PutMapping("/{id}")
    public ResponseEntity<Atraccion> actualizarAtraccion(@PathVariable Long id, @RequestBody Atraccion atraccion) {
        if (!atraccionService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atraccion.setId(id);
        Atraccion atraccionActualizada = atraccionService.guardarAtraccion(atraccion);
        return ResponseEntity.ok(atraccionActualizada);
    }

    // Eliminar atracción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAtraccion(@PathVariable Long id) {
        if (!atraccionService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        atraccionService.eliminarAtraccion(id);
        return ResponseEntity.noContent().build();
    }
}