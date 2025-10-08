package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password; // ⚡ nueva: el admin puede establecerla
    private String fullName;
    private String email;
    private Boolean active;
    private String roleName; // ADMIN, CLIENT, etc.
}
