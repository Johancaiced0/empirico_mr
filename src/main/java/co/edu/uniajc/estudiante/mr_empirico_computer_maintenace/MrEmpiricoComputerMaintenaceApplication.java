package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MrEmpiricoComputerMaintenaceApplication {

    // Inyecta el puerto del servidor desde application.properties
    @Value("${server.port}")
    private int serverPort;

    // Inyecta la URL de la base de datos
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    public static void main(String[] args) {
        SpringApplication.run(MrEmpiricoComputerMaintenaceApplication.class, args);
    }

    // Evento que se dispara cuando la app ya está lista
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Backend conectado correctamente");
        System.out.println("Servidor corriendo en http://localhost:" + serverPort);
        System.out.println("Base de datos conectada en: " + datasourceUrl);
    }
    
}
