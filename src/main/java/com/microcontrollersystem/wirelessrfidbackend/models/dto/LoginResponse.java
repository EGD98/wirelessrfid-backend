package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String name;
    private String email;
    private String userName;
    private String idUserType;
    private String idCorporation;

    public static LoginResponse asDTO(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId().toString());
        loginResponse.setName(user.getName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setUserName(user.getUserName());
        loginResponse.setIdUserType(user.getIdUserType().toString());
        loginResponse.setIdCorporation(user.getIdCorporation().toString());
        return loginResponse;
    }
}
