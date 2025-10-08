package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Maintenance;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
}
