package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.ScheduleData;
import com.microcontrollersystem.wirelessrfidbackend.models.dto.SpaceData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Schedule;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
import com.microcontrollersystem.wirelessrfidbackend.repositories.ClientRepository;
import com.microcontrollersystem.wirelessrfidbackend.repositories.ScheduleRepository;
import com.microcontrollersystem.wirelessrfidbackend.repositories.SpaceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ClientService clientService;
    private final SpaceService spaceService;

    public List<Schedule> getScheduleList() {
        return scheduleRepository.findAllByStatusTrue();
    }

    public String addSchedule(ScheduleData scheduleData, Integer idUser) {
        Schedule schedule;
        Client client = clientService.getClientById(Integer.parseInt(scheduleData.getClientData().getId()));
        ClientData clientData = ClientData.from(client);
        scheduleData.setClientData(clientData);
        Space space = spaceService.getSpaceById(Integer.parseInt(scheduleData.getSpaceData().getId()));
        SpaceData spaceData = SpaceData.from(space);
        scheduleData.setSpaceData(spaceData);
        if (!scheduleData.getId().isEmpty()) {
            schedule = scheduleRepository.
                    findById(Integer.parseInt(scheduleData.getId())).
                    orElseThrow(() -> new RuntimeException("Horario no existe"));
            schedule = scheduleData.asDTO(schedule);
        } else {
            schedule = scheduleData.asDTO();
        }
        if (scheduleData.getId() != null) {
            schedule.setEditDate(new Date());
            schedule.setEditorUser(idUser);
        } else {
            schedule.setCreatorUser(idUser);
        }
        Schedule scheduleSaved = scheduleRepository.save(schedule);
        String answer = schedule.getId() != null ? "Horario actualizado con exito" : "Horario creado con exito";
        return Objects.nonNull(scheduleSaved.getId()) ? answer
                : "Ha ocurrido un error al intentar crear el horario";
    }

    public String deleteSchedule(Integer idUserEditor, Integer idSpace) {
        Schedule schedule = scheduleRepository.findById(idSpace).orElseThrow(() -> new RuntimeException("Espacio no existe"));
        schedule.setStatus(Boolean.FALSE);
        schedule.setEditorUser(idUserEditor);
        schedule.setEditDate(new Date());
        Schedule spaceSaved = scheduleRepository.save(schedule);
        String respuesta = "Espacio eliminado con exito";
        return Objects.nonNull(spaceSaved.getId()) ? respuesta
                : "Ha ocurrido un error al intentar eliminar el Espacio";
    }
}
