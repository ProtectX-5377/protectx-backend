package com.protectx.cameras.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cameras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false, unique = true)
    private String ipAddress;

    @Column(nullable = false)
    private String status; // ONLINE, OFFLINE, DISABLED

    @Column(nullable = false)
    private String type; // INDOOR, OUTDOOR, PTZ, etc.

    private String resolution;
    private String model;
    private String manufacturer;

    // Si las cámaras pertenecen a usuarios, agregar:
    // @ManyToOne
    // @JoinColumn(name = "user_id")
    // private User user;
}