package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.SpaceData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import com.microcontrollersystem.wirelessrfidbackend.services.SpaceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api")
public class SpaceController {
    private JWTUtil jwtUtil;
    private SpaceService spaceService;

    @PostMapping(value = "/spaceList")
    public ResponseEntity<Object> getClientList(@RequestHeader("Authorization") String token ) {
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e) {
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<Space> spaces = spaceService.getSpaceList();
        List<SpaceData> spaceDataList = new ArrayList<>();
        for (Space space : spaces){
            SpaceData spaceData = SpaceData.from(space);
            spaceDataList.add(spaceData);
        }
        return new ResponseEntity<>(spaceDataList, HttpStatus.OK);
    }

    @PostMapping(value = "/space")
    public ResponseEntity<String> addSpace(@RequestHeader("Authorization") String token, @RequestBody SpaceData spaceData) {
        Integer idUser = null;
        try {
            jwtUtil.validateToken(token);
            idUser = jwtUtil.getTokenIdUser(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = spaceService.addSpace(spaceData, idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteSpace")
    public ResponseEntity<String> deleteSpace(@RequestHeader("Authorization") String token, @RequestBody Integer idSpace) {
        Integer idUserEditor = null;
        try {
            jwtUtil.validateToken(token);
            idUserEditor = jwtUtil.getTokenIdUser(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = spaceService.deleteSpace(idUserEditor, idSpace);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
