package com.protectx.alerts.interfaces.rest;

import com.protectx.alerts.domain.model.Alert;
import com.protectx.alerts.domain.service.AlertService;
import com.protectx.alerts.interfaces.rest.dto.AlertDto;
import com.protectx.alerts.interfaces.rest.dto.CreateAlertDto;
import com.protectx.alerts.interfaces.rest.transform.AlertDtoAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public ResponseEntity<AlertDto> createAlert(@RequestBody CreateAlertDto createAlertDto) {
        Alert alert = AlertDtoAssembler.toEntity(createAlertDto);
        Alert createdAlert = alertService.createAlert(alert);
        return new ResponseEntity<>(AlertDtoAssembler.toDto(createdAlert), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertDto> getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id)
                .map(alert -> ResponseEntity.ok(AlertDtoAssembler.toDto(alert)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlerts() {
        List<AlertDto> alerts = alertService.getAllAlerts().stream()
                .map(AlertDtoAssembler::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alerts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertDto> updateAlert(@PathVariable Long id, @RequestBody CreateAlertDto createAlertDto) {
        Alert alert = AlertDtoAssembler.toEntity(createAlertDto);
        Alert updatedAlert = alertService.updateAlert(id, alert);
        return ResponseEntity.ok(AlertDtoAssembler.toDto(updatedAlert));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}
