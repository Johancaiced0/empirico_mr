package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceType;
import lombok.Data;

@Data
public class MaintenanceRequest {
    private Integer deviceId;
    private String problemDescription;
    private MaintenanceType type;
    private Integer technicianId;
    private Integer supervisorId;
}
