package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance")
@Data
@NoArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(name = "problem_description")
    private String problemDescription;

    @Enumerated(EnumType.STRING)
    private MaintenanceType type = MaintenanceType.CORRECTIVE;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status = MaintenanceStatus.CREATED;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @Column(name = "resolution_notes")
    private String resolutionNotes;
}
