package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto.MaintenanceHistoryRequest;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto.MaintenanceHistoryResponse;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Maintenance;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceHistory;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.MaintenanceHistoryRepository;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.MaintenanceRepository;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceHistoryService {

    private final MaintenanceHistoryRepository historyRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final UserRepository userRepository;

    public MaintenanceHistoryResponse createHistory(MaintenanceHistoryRequest request) {
        Maintenance maintenance = maintenanceRepository.findById(request.getMaintenanceId())
                .orElseThrow(() -> new RuntimeException("Maintenance not found"));

        User user = null;
        if (request.getUserId() != null) {
            user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        MaintenanceHistory history = new MaintenanceHistory();
        history.setMaintenance(maintenance);
        history.setUser(user);
        history.setDescription(request.getDescription());
        history.setDetails(request.getDetails());

        MaintenanceHistory saved = historyRepository.save(history);

        MaintenanceHistoryResponse response = new MaintenanceHistoryResponse();
        response.setId(saved.getId());
        response.setMaintenanceId(saved.getMaintenance().getId());
        response.setUserId(saved.getUser() != null ? saved.getUser().getId() : null);
        response.setDescription(saved.getDescription());
        response.setCreatedAt(saved.getCreatedAt());
        response.setDetails(saved.getDetails());
        return response;
    }

    public List<MaintenanceHistoryResponse> getByMaintenanceId(Integer maintenanceId) {
        return historyRepository.findByMaintenanceId(maintenanceId).stream().map(h -> {
            MaintenanceHistoryResponse r = new MaintenanceHistoryResponse();
            r.setId(h.getId());
            r.setMaintenanceId(h.getMaintenance().getId());
            r.setUserId(h.getUser() != null ? h.getUser().getId() : null);
            r.setDescription(h.getDescription());
            r.setCreatedAt(h.getCreatedAt());
            r.setDetails(h.getDetails());
            return r;
        }).collect(Collectors.toList());
    }

    public List<MaintenanceHistoryResponse> getAllHistories() {
    return historyRepository.findAll().stream().map(h -> {
        MaintenanceHistoryResponse r = new MaintenanceHistoryResponse();
        r.setId(h.getId());
        r.setMaintenanceId(h.getMaintenance().getId());
        r.setUserId(h.getUser() != null ? h.getUser().getId() : null);
        r.setDescription(h.getDescription());
        r.setCreatedAt(h.getCreatedAt());
        r.setDetails(h.getDetails());
        return r;
    }).collect(Collectors.toList());
}

}
