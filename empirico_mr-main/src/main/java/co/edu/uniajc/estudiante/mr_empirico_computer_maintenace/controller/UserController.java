package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Listar todos
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo
    @PostMapping("/register")
    public User create(@RequestBody User user) {
        // Encriptar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Actualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setFullName(userDetails.getFullName());
                    user.setEmail(userDetails.getEmail());
                    user.setActive(userDetails.getActive());
                    user.setRole(userDetails.getRole());
                
                    // Solo encriptar si viene una contraseña nueva
                    if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
                        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
                    }
                
                    User updated = userRepository.save(user);
                    updated.setPassword(null); // nunca retornar el hash
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userRepository.findById(id).ifPresentOrElse(
            userRepository::delete,
            () -> {}
        );

        // Retornamos 204 si existía y se borró, 404 si no
        return userRepository.existsById(id) ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}
