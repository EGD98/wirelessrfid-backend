package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.services.ClientService;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
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
public class ClientController {
    private ClientService clientService;
    private JWTUtil jwtUtil;

    @PostMapping(value = "/clientList")
    public ResponseEntity<Object> getClientList(@RequestHeader("Authorization") String token ) {
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        List<Client> clients = clientService.getClientList();
        List<ClientData> clientDataList = new ArrayList<>();
        for (Client client : clients){
            ClientData clientData = ClientData.from(client);
            clientDataList.add(clientData);
        }
        return new ResponseEntity<>(clientDataList, HttpStatus.OK);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<String> addUser(@RequestHeader("Authorization") String token, @RequestBody ClientData clientData) {
        Integer idUser = null;
        try {
            jwtUtil.validateToken(token);
            idUser = jwtUtil.getTokenIdUser(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = clientService.addClient(clientData, idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteClient")
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token, @RequestBody Integer idUser) {
        Integer idUserEditor = null;
        try {
            jwtUtil.validateToken(token);
            idUserEditor = jwtUtil.getTokenIdUser(token);
        } catch (Exception e){
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = clientService.deleteClient(idUserEditor, idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
