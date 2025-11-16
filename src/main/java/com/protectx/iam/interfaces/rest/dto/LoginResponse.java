package com.protectx.iam.interfaces.rest.dto;

import com.protectx.iam.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private User user;
}