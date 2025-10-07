package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private User user;
}
