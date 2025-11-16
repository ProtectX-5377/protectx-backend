package com.protectx.cameras.interfaces.rest;

import com.protectx.cameras.domain.model.Camera;
import com.protectx.cameras.infrastructure.persistence.CameraRepository;
import com.protectx.cameras.interfaces.rest.dto.CameraRequest;
import com.protectx.cameras.interfaces.rest.dto.CameraResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cameras")
@CrossOrigin(origins = "http://localhost:4200")
public class CameraController {

    private final CameraRepository cameraRepository;

    public CameraController(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @GetMapping
    public List<Camera> getAllCameras() {
        return cameraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Camera> getCameraById(@PathVariable Long id) {
        return cameraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCamera(@RequestBody CameraRequest request) {
        if (cameraRepository.existsByIpAddress(request.getIpAddress())) {
            return ResponseEntity.badRequest().body("La dirección IP ya está en uso");
        }

        Camera newCamera = Camera.builder()
                .name(request.getName())
                .location(request.getLocation())
                .ipAddress(request.getIpAddress())
                .status("ONLINE") // Estado por defecto
                .type(request.getType())
                .resolution(request.getResolution())
                .model(request.getModel())
                .manufacturer(request.getManufacturer())
                .build();

        Camera savedCamera = cameraRepository.save(newCamera);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCamera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCamera(@PathVariable Long id, @RequestBody CameraRequest request) {
        return cameraRepository.findById(id)
                .map(existingCamera -> {
                    // Verificar si la IP ya está en uso por otra cámara
                    if (!existingCamera.getIpAddress().equals(request.getIpAddress()) &&
                            cameraRepository.existsByIpAddress(request.getIpAddress())) {
                        return ResponseEntity.badRequest().body("La dirección IP ya está en uso");
                    }

                    existingCamera.setName(request.getName());
                    existingCamera.setLocation(request.getLocation());
                    existingCamera.setIpAddress(request.getIpAddress());
                    existingCamera.setType(request.getType());
                    existingCamera.setResolution(request.getResolution());
                    existingCamera.setModel(request.getModel());
                    existingCamera.setManufacturer(request.getManufacturer());

                    return ResponseEntity.ok(cameraRepository.save(existingCamera));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable Long id) {
        if (cameraRepository.existsById(id)) {
            cameraRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public List<Camera> getCamerasByStatus(@PathVariable String status) {
        return cameraRepository.findByStatus(status.toUpperCase());
    }

    @GetMapping("/type/{type}")
    public List<Camera> getCamerasByType(@PathVariable String type) {
        return cameraRepository.findByType(type.toUpperCase());
    }
}