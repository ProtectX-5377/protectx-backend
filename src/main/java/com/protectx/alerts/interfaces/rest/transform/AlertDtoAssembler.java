package com.protectx.alerts.interfaces.rest.transform;

import com.protectx.alerts.domain.model.Alert;
import com.protectx.alerts.interfaces.rest.dto.AlertDto;
import com.protectx.alerts.interfaces.rest.dto.CreateAlertDto;

public class AlertDtoAssembler {

    public static Alert toEntity(CreateAlertDto dto) {
        Alert alert = new Alert();
        alert.setType(dto.getType());
        alert.setMessage(dto.getMessage());
        alert.setLocation(dto.getLocation());
        alert.setDate(dto.getDate());
        alert.setTime(dto.getTime());
        alert.setSeverity(dto.getSeverity());
        return alert;
    }

    public static AlertDto toDto(Alert entity) {
        AlertDto dto = new AlertDto();
        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setMessage(entity.getMessage());
        dto.setLocation(entity.getLocation());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setSeverity(entity.getSeverity());
        return dto;
    }
}
