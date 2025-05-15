package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Registro de nuevos clientes
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Cliente cliente) {
        try {
            Cliente nuevo = authService.registrarUsuario(
                    cliente.getNombre(),
                    cliente.getApellidos(),
                    cliente.getEmail(),
                    cliente.getTelefono(),
                    cliente.getPassword()
            );
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login de clientes existentes
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Cliente loginReq) {
        boolean ok = authService.login(loginReq.getEmail(), loginReq.getPassword());
        if (!ok) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
        Optional<Cliente> opt = authService.getClientePorEmail(loginReq.getEmail());
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body("Cliente no encontrado");
        }
        Cliente c = opt.get();
        // Define aquí cómo asignas ADMIN vs USER
        String rol = c.getEmail().equalsIgnoreCase("admin@divercity.com") ? "ADMIN" : "USER";
        return ResponseEntity.ok(new LoginResponse("Login exitoso", rol, c.getNombre(), c.getId()));

    }

    // DTO para la respuesta del login
    static class LoginResponse {
        public String message;
        public String role;
        public String nombre;
        public Long id; // nuevo campo

        public LoginResponse(String message, String role, String nombre, Long id) {
            this.message = message;
            this.role = role;
            this.nombre = nombre;
            this.id = id;
        }
    }
}
