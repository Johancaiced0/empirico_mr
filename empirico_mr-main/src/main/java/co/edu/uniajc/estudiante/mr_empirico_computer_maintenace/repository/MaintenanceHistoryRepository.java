package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceHistory;

@Repository
public interface MaintenanceHistoryRepository extends JpaRepository<MaintenanceHistory, Integer> {
    List<MaintenanceHistory> findByMaintenanceId(Integer maintenanceId);
}
