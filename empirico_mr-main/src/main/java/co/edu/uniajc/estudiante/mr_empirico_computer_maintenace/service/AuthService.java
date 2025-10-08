package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.UserRepository;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Autentica al usuario y devuelve el objeto User si las credenciales son correctas.
     * @param email Email del usuario
     * @param password Contraseña sin encriptar
     * @return Usuario autenticado
     * @throws RuntimeException si el usuario no existe o la contraseña es incorrecta
     */
    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }

    /**
     * Genera un token JWT para el usuario autenticado
     * @param user Usuario autenticado
     * @return Token JWT
     */
    public String generateToken(User user) {
        return jwtService.generateToken(user.getEmail());
    }

    /**
     * Registra un nuevo usuario en la base de datos
     * @param user Objeto User con datos a guardar
     * @return Usuario registrado
     */
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
