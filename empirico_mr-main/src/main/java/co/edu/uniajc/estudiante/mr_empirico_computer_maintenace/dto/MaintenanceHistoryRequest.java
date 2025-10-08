package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import lombok.Data;

@Data
public class MaintenanceHistoryRequest {
    private Integer maintenanceId;
    private Integer userId;
    private String description;
    private String details; // JSON como string
}
