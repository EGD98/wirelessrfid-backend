package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailUser;
    private String password;
}
