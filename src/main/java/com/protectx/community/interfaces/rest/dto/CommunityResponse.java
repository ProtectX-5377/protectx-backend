package com.protectx.community.interfaces.rest.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityResponse {
    private String name;
    private String description;
    private int capacity;
    private String createdAt;
    private Long OwnerID;
}
