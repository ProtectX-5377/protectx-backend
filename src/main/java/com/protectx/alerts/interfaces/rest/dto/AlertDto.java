package com.protectx.alerts.interfaces.rest.dto;

import lombok.Data;

@Data
public class AlertDto {
    private Long id;
    private String type;
    private String message;
    private String location;
    private String date;
    private String time;
    private Integer severity;
}
