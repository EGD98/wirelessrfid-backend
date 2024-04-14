package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import lombok.Data;

@Data
public class UserData {
    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String email;
    private String idUserType;
    private String idCorporation;

    public static UserData asDTO(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId().toString());
        userData.setName(user.getName());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getUserName());
        userData.setPhoneNumber(user.getPhoneNumber().toString());
        userData.setEmail(user.getEmail());
        userData.setIdUserType(user.getIdUserType().toString());
        userData.setIdCorporation(user.getIdCorporation().toString());
        return userData;
    }
}
