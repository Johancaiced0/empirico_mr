package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto.AuthRequest;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto.AuthResponse;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            User user = authService.authenticateUser(request.getEmail(), request.getPassword());
            String token = authService.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(token, user)); // devolvemos el User completo
        } catch (RuntimeException ex) {
            String msg = ex.getMessage();
            if (msg.contains("Usuario no encontrado")) {
                return ResponseEntity.status(404).body(new AuthResponse("Error: Usuario no encontrado", null));
            } else if (msg.contains("Contraseña incorrecta")) {
                return ResponseEntity.status(401).body(new AuthResponse("Error: Contraseña incorrecta", null));
            } else {
                return ResponseEntity.status(500).body(new AuthResponse("Error interno del servidor", null));
            }
        }
    }
}
