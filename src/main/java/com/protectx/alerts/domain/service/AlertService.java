package com.protectx.alerts.domain.service;

import com.protectx.alerts.domain.model.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertService {
    Alert createAlert(Alert alert);
    Optional<Alert> getAlertById(Long id);
    List<Alert> getAllAlerts();
    Alert updateAlert(Long id, Alert alert);
    void deleteAlert(Long id);
}
