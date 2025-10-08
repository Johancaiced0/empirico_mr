package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
