package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginResponse;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import com.microcontrollersystem.wirelessrfidbackend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class OauthController {
    private final JWTUtil jwtUtil;
    private final UserService userService;

    @PostMapping(value = "/loginRequest")
    public ResponseEntity<Object>  loginRequest (@RequestBody LoginRequest loginRequest){
        try {
            User user = userService.validateUser(loginRequest);
            String token = null;
            if (Objects.nonNull(user)) {
                token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
            }
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ha ocurrido un error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
