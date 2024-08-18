package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.RegistryData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.RfidRequest;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import com.microcontrollersystem.wirelessrfidbackend.services.ResgistryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.modeler.Registry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/api")
public class RegistryController {
    private JWTUtil jwtUtil;
    private ResgistryService resgistryService;

    @PostMapping(value = "/registryList")
    public ResponseEntity<Object> getResgistryList(@RequestHeader("Authorization") String token ) {
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        List<RfidRequest> rfidRequests = resgistryService.getRegistryList();
        List<RegistryData> registryDataList = new ArrayList<>();
        for (RfidRequest rfidRequest : rfidRequests){
            RegistryData registryData = RegistryData.from(rfidRequest);
            registryDataList.add(registryData);
        }
        return new ResponseEntity<>(registryDataList, HttpStatus.OK);
    }
}
