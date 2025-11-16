package com.protectx.alerts.domain.service;

import com.protectx.alerts.domain.model.Alert;
import com.protectx.alerts.domain.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    @Override
    public Optional<Alert> getAlertById(Long id) {
        return alertRepository.findById(id);
    }

    @Override
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    @Override
    public Alert updateAlert(Long id, Alert alert) {
        return alertRepository.findById(id).map(existingAlert -> {
            existingAlert.setType(alert.getType());
            existingAlert.setMessage(alert.getMessage());
            existingAlert.setLocation(alert.getLocation());
            existingAlert.setDate(alert.getDate());
            existingAlert.setTime(alert.getTime());
            existingAlert.setSeverity(alert.getSeverity());
            return alertRepository.save(existingAlert);
        }).orElseThrow(() -> new RuntimeException("Alert not found with id " + id));
    }

    @Override
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
}
