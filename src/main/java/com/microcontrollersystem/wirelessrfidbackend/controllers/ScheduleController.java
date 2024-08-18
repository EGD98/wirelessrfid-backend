package com.microcontrollersystem.wirelessrfidbackend.controllers;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.ScheduleData;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.SpaceData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Schedule;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
import com.microcontrollersystem.wirelessrfidbackend.services.ClientService;
import com.microcontrollersystem.wirelessrfidbackend.services.JWTUtil;
import com.microcontrollersystem.wirelessrfidbackend.services.ScheduleService;
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
public class ScheduleController {
    private JWTUtil jwtUtil;
    private ScheduleService scheduleService;
    private ClientService clientService;
    private SpaceService spaceService;

    @PostMapping(value = "/scheduleList")
    public ResponseEntity<Object> getScheduleList(@RequestHeader("Authorization") String token) {
        try {
            jwtUtil.validateToken(token);
        } catch (Exception e) {
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        List<Schedule> schedules = scheduleService.getScheduleList();
        List<ScheduleData> scheduleDataList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            Client client = clientService.getClientById(schedule.getIdClient());
            ClientData clientData =  ClientData.from(client);
            Space space = spaceService.getSpaceById(schedule.getIdSpace());
            SpaceData spaceData = SpaceData.from(space);
            ScheduleData scheduleData = ScheduleData.from(schedule,clientData,spaceData);
            scheduleDataList.add(scheduleData);
        }
        return new ResponseEntity<>(scheduleDataList, HttpStatus.OK);
    }

    @PostMapping(value = "/schedule")
    public ResponseEntity<String> addSchedule(@RequestHeader("Authorization") String token, @RequestBody ScheduleData scheduleData) {
        Integer idUser = null;
        try {
            jwtUtil.validateToken(token);
            idUser = jwtUtil.getTokenIdUser(token);
        } catch (Exception e) {
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = scheduleService.addSchedule(scheduleData, idUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deleteSchedule")
    public ResponseEntity<String> deleteSchedule(@RequestHeader("Authorization") String token, @RequestBody Integer idSpace) {
        Integer idUserEditor = null;
        try {
            jwtUtil.validateToken(token);
            idUserEditor = jwtUtil.getTokenIdUser(token);
        } catch (Exception e) {
            log.error("TOKEN UNAUTHORIZED {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String response = scheduleService.deleteSchedule(idUserEditor, idSpace);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
