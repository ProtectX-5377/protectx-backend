package com.protectx.cameras.interfaces.rest.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CameraResponse {
    private Long id;
    private String name;
    private String location;
    private String ipAddress;
    private String status;
    private String type;
    private String resolution;
    private String model;
    private String manufacturer;

    // private Long userId; // Si hay relación
    // private String userName; // Si hay relación
}