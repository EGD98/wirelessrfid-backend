package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.UserData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import com.microcontrollersystem.wirelessrfidbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        List<User> users = userService.getUserList();
        List<UserData> userDataList = new ArrayList<>();
        for (User user : users){
            UserData userData = UserData.from(user);
            userDataList.add(userData);
        }
        return new ResponseEntity<>(userDataList, HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> addUser(@RequestHeader("Authorization") String token, @RequestBody UserData userData) {
        Integer idUser = null;
        try {
            jwtUtil.validateToken(token);
            idUser = jwtUtil.getTokenIdUser(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = userService.addUser(userData, idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token, @RequestBody Integer idUser) {
        Integer idUserEditor = null;
        try {
            jwtUtil.validateToken(token);
            idUserEditor = jwtUtil.getTokenIdUser(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = userService.deleteUser(idUserEditor, idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
