package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
