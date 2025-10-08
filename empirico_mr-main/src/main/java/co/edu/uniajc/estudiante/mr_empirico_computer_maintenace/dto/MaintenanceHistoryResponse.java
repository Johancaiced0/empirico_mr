package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MaintenanceHistoryResponse {
    private Integer id;
    private Integer maintenanceId;
    private Integer userId;
    private String description;
    private LocalDateTime createdAt;
    private String details;
}
