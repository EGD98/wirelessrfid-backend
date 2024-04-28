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
    private String password;
    private String email;
    private String idUserType;
    private String idCorporation;

    public static UserData from(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId().toString());
        userData.setName(user.getName());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setUserName(user.getUserName());
        userData.setPhoneNumber(user.getPhoneNumber().toString());
        userData.setEmail(user.getEmail());
        userData.setIdUserType(user.getIdUserType().toString());
        userData.setIdCorporation(user.getIdCorporation().toString());
        return userData;
    }

    public User asDTO() {
        User user = new User();
        user.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        user.setName(this.name);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setUserName(this.userName);
        user.setPhoneNumber(Long.valueOf(this.phoneNumber));
        user.setEmail(this.email);
        user.setIdUserType(Integer.valueOf(this.idUserType));
        user.setIdCorporation(Integer.valueOf(this.idCorporation));
        user.setPassword(this.password);
        return user;
    }
    public User asDTO(User user) {
        user.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        user.setName(this.name);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setUserName(this.userName);
        user.setPhoneNumber(Long.valueOf(this.phoneNumber));
        user.setEmail(this.email);
        user.setIdUserType(Integer.valueOf(this.idUserType));
        user.setIdCorporation(Integer.valueOf(this.idCorporation));
        user.setPassword(this.password);
        return user;
    }
}
