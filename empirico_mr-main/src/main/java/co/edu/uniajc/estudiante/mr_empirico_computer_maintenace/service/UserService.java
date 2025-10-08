package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
