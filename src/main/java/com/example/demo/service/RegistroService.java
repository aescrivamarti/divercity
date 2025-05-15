package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    @Autowired
    private ClienteRepository clienteRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método para registrar un cliente
    public Cliente registrarUsuario(String nombre, String apellidos, String email, String telefono, String password
    ) {
        // Encriptamos la contraseña
        String passwordEncriptada = passwordEncoder.encode(password);

        // Creamos un nuevo cliente con los datos
        Cliente cliente = new Cliente(nombre, apellidos, email, telefono, passwordEncriptada);

        // Guardamos el cliente en la base de datos
        return clienteRepository.save(cliente);
    }
}
