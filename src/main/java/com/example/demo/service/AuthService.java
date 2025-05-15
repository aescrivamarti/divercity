package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean login(String email, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Cliente registrarUsuario(String nombre, String apellidos, String email, String telefono, String password) {
        clienteRepository.findByEmail(email).ifPresent(c -> {
            throw new RuntimeException("El correo ya está registrado");
        });
        Cliente c = new Cliente(nombre, apellidos, email, telefono, passwordEncoder.encode(password));
        return clienteRepository.save(c);
    }

    public Optional<Cliente> getClientePorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
