package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String root() {
        return """
        {
            "public_routes": {
                "GET /": "Home info (this page)",
                "POST /api/auth/login": "Authenticate user",
                "POST /api/users/register": "Register new user"
            },

            "private_routes": {
                "users": {
                    "GET /api/users": "List all users",
                    "GET /api/users/{id}": "Get user by ID",
                    "POST /api/users": "Create a new user",
                    "PUT /api/users/{id}": "Update existing user (email cannot change)",
                    "DELETE /api/users/{id}": "Delete user by ID"
                },

                "devices": {
                    "GET /api/devices": "List all devices",
                    "GET /api/devices/{id}": "Get device by ID",
                    "POST /api/devices": "Create a new device",
                    "PUT /api/devices/{id}": "Update existing device",
                    "DELETE /api/devices/{id}": "Delete device by ID"
                },

                "maintenances": {
                    "GET /api/maintenances": "List all maintenances",
                    "GET /api/maintenances/{id}": "Get maintenance by ID",
                    "POST /api/maintenances/create?deviceId=&adminId=": "Create a new maintenance ticket",
                    "PUT /api/maintenances/updateStatus?maintenanceId=&status=&technicianId=": "Update maintenance status",
                    "DELETE /api/maintenances/{id}": "Delete maintenance by ID"
                }
            }
        }
        """;
    }
}
