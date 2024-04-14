package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.UserData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import com.microcontrollersystem.wirelessrfidbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private JWTUtil jwtUtil;

    @PostMapping(value = "/userList")
    public ResponseEntity<Object> getUserList(@RequestHeader("Authorization") String token ) {
        try{
            jwtUtil.validateToken(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        List<User> users = userService.getUserList();
        List<UserData> userDataList = new ArrayList<>();
        for (User user : users){
            UserData userData = UserData.asDTO(user);
            userDataList.add(userData);
        }
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }
}
