package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Schedule;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;

@Data
public class ScheduleData {
    private String id;
    private String idClient;
    private String startDate;
    private String endDate;
    private String startHour;
    private String endHour;
    private String idSpace;
    private String rfidCode;

    public static ScheduleData from(Schedule schedule) {
        ScheduleData scheduleData = new ScheduleData();
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatterHour = new SimpleDateFormat("HH:mm");
        scheduleData.setId(String.valueOf(schedule.getId()));
        scheduleData.setIdClient(String.valueOf(schedule.getIdClient()));
        scheduleData.setStartDate(outputFormatterDate.format(schedule.getStartDate()));
        scheduleData.setEndDate(outputFormatterDate.format(schedule.getEndDate()));
        scheduleData.setStartHour(outputFormatterHour.format(schedule.getStartHour()));
        scheduleData.setEndHour(outputFormatterHour.format(schedule.getEndHour()));
        scheduleData.setIdSpace(String.valueOf(schedule.getIdSpace()));
        scheduleData.setRfidCode(schedule.getRfidCode());
        return scheduleData;
    }

    @SneakyThrows
    public Schedule asDTO(Schedule schedule) {
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatterHour = new SimpleDateFormat("HH:mm");
        schedule.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        schedule.setIdClient(Integer.valueOf(this.idClient));
        schedule.setStartDate(outputFormatterDate.parse(this.getStartDate()));
        schedule.setEndDate(outputFormatterDate.parse(this.getEndDate()));
        schedule.setStartHour(outputFormatterHour.parse(this.getStartHour()));
        schedule.setEndHour(outputFormatterHour.parse(this.getEndHour()));
        schedule.setIdSpace(Integer.parseInt(this.getIdSpace()));
        schedule.setRfidCode(this.getRfidCode());
        return schedule;
    }

    @SneakyThrows
    public Schedule asDTO() {
        Schedule schedule = new Schedule();
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormatterHour = new SimpleDateFormat("HH:mm");
        schedule.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        schedule.setIdClient(Integer.valueOf(this.idClient));
        schedule.setStartDate(outputFormatterDate.parse(this.getStartDate()));
        schedule.setEndDate(outputFormatterDate.parse(this.getEndDate()));
        schedule.setStartHour(outputFormatterHour.parse(this.getStartHour()));
        schedule.setEndHour(outputFormatterHour.parse(this.getEndHour()));
        schedule.setIdSpace(Integer.parseInt(this.getIdSpace()));
        schedule.setRfidCode(this.getRfidCode());
        return schedule;
    }
}
