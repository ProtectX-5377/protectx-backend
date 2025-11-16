package com.protectx.community.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false, updatable = false)
    private Long Id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private Long ownerId;
}


