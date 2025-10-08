package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceStatus;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.MaintenanceType;
import lombok.Data;

@Data
public class MaintenanceResponse {
    private Integer id;
    private Integer deviceId;
    private String problemDescription;
    private MaintenanceType type;
    private MaintenanceStatus status;
    private Integer technicianId;
    private Integer supervisorId;
    private Integer adminId;
    private String resolutionNotes;
}
