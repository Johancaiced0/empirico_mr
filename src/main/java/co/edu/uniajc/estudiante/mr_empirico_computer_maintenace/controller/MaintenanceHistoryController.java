package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto.MaintenanceHistoryRequest;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto.MaintenanceHistoryResponse;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service.MaintenanceHistoryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maintenance-history")
@RequiredArgsConstructor
public class MaintenanceHistoryController {

    private final MaintenanceHistoryService historyService;

    @GetMapping("/all")
    public ResponseEntity<List<MaintenanceHistoryResponse>> getAllHistories() {
        return ResponseEntity.ok(historyService.getAllHistories());
    }


    @PostMapping("/create")
    public ResponseEntity<MaintenanceHistoryResponse> create(@RequestBody MaintenanceHistoryRequest request) {
        return ResponseEntity.ok(historyService.createHistory(request));
    }

    @GetMapping("/maintenance/{maintenanceId}")
    public ResponseEntity<List<MaintenanceHistoryResponse>> getByMaintenance(@PathVariable Integer maintenanceId) {
        return ResponseEntity.ok(historyService.getByMaintenanceId(maintenanceId));
    }
}
