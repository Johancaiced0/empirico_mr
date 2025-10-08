package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
