package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Schedule;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

@Data
public class ScheduleData {
    private String id;
    private ClientData clientData;
    private String startDate;
    private String endDate;
    private String startHour;
    private String endHour;
    private SpaceData spaceData;
    private String rfidCode;

    public static ScheduleData from(Schedule schedule,ClientData clientData,SpaceData spaceData) {
        ScheduleData scheduleData = new ScheduleData();
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatterHour = new SimpleDateFormat("HH:mm");
        scheduleData.setId(String.valueOf(schedule.getId()));
        scheduleData.setClientData(clientData);
        scheduleData.setStartDate(outputFormatterDate.format(schedule.getStartDate()));
        scheduleData.setEndDate(outputFormatterDate.format(schedule.getEndDate()));
        scheduleData.setStartHour(outputFormatterHour.format(schedule.getStartHour()));
        scheduleData.setEndHour(outputFormatterHour.format(schedule.getEndHour()));
        scheduleData.setSpaceData(spaceData);
        scheduleData.setRfidCode(schedule.getRfidCode());
        return scheduleData;
    }

    @SneakyThrows
    public Schedule asDTO(Schedule schedule) {
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatterHour = new SimpleDateFormat("HH:mm");
        schedule.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        schedule.setIdClient(Integer.valueOf(this.clientData.getId()));
        schedule.setStartDate(outputFormatterDate.parse(this.getStartDate()));
        schedule.setEndDate(outputFormatterDate.parse(this.getEndDate()));
        schedule.setStartHour(outputFormatterHour.parse(this.getStartHour()));
        schedule.setEndHour(outputFormatterHour.parse(this.getEndHour()));
        schedule.setIdSpace(Integer.parseInt(this.spaceData.getId()));
        schedule.setRfidCode(this.getRfidCode());
        return schedule;
    }

    @SneakyThrows
    public Schedule asDTO() {
        Schedule schedule = new Schedule();
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatterHour = new SimpleDateFormat("HH:mm");
        schedule.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        schedule.setIdClient(Integer.valueOf(this.clientData.getId()));
        schedule.setStartDate(outputFormatterDate.parse(this.getStartDate()));
        schedule.setEndDate(outputFormatterDate.parse(this.getEndDate()));
        schedule.setStartHour(outputFormatterHour.parse(this.getStartHour()));
        schedule.setEndHour(outputFormatterHour.parse(this.getEndHour()));
        schedule.setIdSpace(Integer.parseInt(this.spaceData.getId()));
        schedule.setRfidCode(this.getRfidCode());
        return schedule;
    }
}
