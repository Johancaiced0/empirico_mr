package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Device;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Maintenance;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceStatus;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.DeviceRepository;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.MaintenanceRepository;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    public List<Maintenance> getAll() {
        return maintenanceRepository.findAll();
    }

    public Maintenance getById(Integer id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));
    }

    public Maintenance create(Maintenance maintenance, Integer deviceId, Integer adminId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Dispositivo no encontrado"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        maintenance.setDevice(device);
        maintenance.setAdmin(admin);

        return maintenanceRepository.save(maintenance);
    }

    public Maintenance updateStatus(Integer maintenanceId, MaintenanceStatus status, Integer technicianId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        User technician = userRepository.findById(technicianId)
                .orElseThrow(() -> new RuntimeException("Técnico no encontrado"));

        maintenance.setStatus(status);
        maintenance.setTechnician(technician);

        return maintenanceRepository.save(maintenance);
    }

    public void delete(Integer id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantenimiento no encontrado"));

        maintenanceRepository.delete(maintenance);
    }
}
