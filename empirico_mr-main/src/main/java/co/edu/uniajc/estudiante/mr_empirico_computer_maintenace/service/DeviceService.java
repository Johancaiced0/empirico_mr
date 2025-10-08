package co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.model.Device;
import co.edu.uniajc.estudiante.mr_empirico_computer_maintenace.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getById(Integer id) {
        return deviceRepository.findById(id);
    }

    public Device create(Device device) {
        device.setCreatedAt(LocalDateTime.now());
        device.setUpdatedAt(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    public Optional<Device> update(Integer id, Device deviceDetails) {
        return deviceRepository.findById(id).map(device -> {
            device.setName(deviceDetails.getName());
            device.setRam(deviceDetails.getRam());
            device.setRom(deviceDetails.getRom());
            device.setHardDrive(deviceDetails.getHardDrive());
            device.setGraphicsCard(deviceDetails.getGraphicsCard());
            device.setSerial(deviceDetails.getSerial());
            device.setClient(deviceDetails.getClient());
            device.setUpdatedAt(LocalDateTime.now());
            return deviceRepository.save(device);
        });
    }

    public boolean delete(Integer id) {
        return deviceRepository.findById(id).map(device -> {
            deviceRepository.delete(device);
            return true;
        }).orElse(false);
    }
}
