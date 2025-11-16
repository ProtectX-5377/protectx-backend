package com.protectx.cameras.interfaces.rest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CameraRequest {
    private String name;
    private String location;
    private String ipAddress;
    private String type;
    private String resolution;
    private String model;
    private String manufacturer;

    // private Long userId; // Si hay relación
}