package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.services.CatalogService;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CatalogController {
    private JWTUtil jwtUtil;
    private final CatalogService catalogService;

    @PostMapping(value = "/getCatalog")
    public ResponseEntity<Object> getCatalog(@RequestHeader("Authorization") String token, @RequestBody String name) {
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<Map<String, Object>> catalogList = catalogService.getCatalog(name);

        return new ResponseEntity<>(catalogList, HttpStatus.OK);
    }
}
