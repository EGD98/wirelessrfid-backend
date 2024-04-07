package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginRequest;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.LoginResponse;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.User;
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
@RequestMapping("/api")
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping(value = "/loginRequest")
    public ResponseEntity<Object> Login (@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.validateUser(loginRequest);
            LoginResponse loginResponse = null;
            if (Objects.nonNull(user)) {
                loginResponse = LoginResponse.asDTO(user);
            }
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Ha ocurrido un error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
