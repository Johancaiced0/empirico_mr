package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Maintenance;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceStatus;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service.MaintenanceService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.getAll();
    }

    @GetMapping("/{id}")
    public Maintenance getById(@PathVariable Integer id) {
        return maintenanceService.getById(id);
    }

    @PostMapping("/create")
    public Maintenance create(@RequestBody Maintenance maintenance,
                              @RequestParam Integer deviceId,
                              @RequestParam Integer adminId) {
        return maintenanceService.create(maintenance, deviceId, adminId);
    }

    @PutMapping("/updateStatus")
    public Maintenance updateStatus(@RequestParam Integer maintenanceId,
                                    @RequestParam String status,
                                    @RequestParam Integer technicianId) {
        return maintenanceService.updateStatus(maintenanceId,
                MaintenanceStatus.valueOf(status.toUpperCase()),
                technicianId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        maintenanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
