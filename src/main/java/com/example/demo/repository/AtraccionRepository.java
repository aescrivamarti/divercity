package com.example.demo.repository;

import com.example.demo.model.Atraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtraccionRepository extends JpaRepository<Atraccion, Long> {
    // Aquí de momento no necesitas más métodos, solo CRUD básico
}