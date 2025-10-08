package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private Boolean active;
    private String roleName;

    public static UserResponse fromEntity(co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getFullName(),
            user.getEmail(),
            user.getActive(),
            user.getRole() != null ? user.getRole().getName() : null
        );
    }
}
