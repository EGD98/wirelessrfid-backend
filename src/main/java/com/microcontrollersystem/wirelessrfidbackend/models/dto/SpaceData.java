package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SpaceData {
    private String id;
    private String roomName;
    private String registrationDate;
    private String places;

    public static SpaceData from(Space space) {
        SpaceData spaceData = new SpaceData();
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        spaceData.setId(String.valueOf(space.getId()));
        spaceData.setRoomName(space.getRoomName());
        spaceData.setRegistrationDate(outputFormatterDate.format(space.getRegistrationDate()));
        spaceData.setPlaces(String.valueOf(space.getPlaces()));
        return spaceData;
    }

    @SneakyThrows
    public Space asDTO(Space space) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        space.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        space.setRoomName(this.roomName);
        Date date = sdf.parse(this.getRegistrationDate());
        space.setRegistrationDate(date);
        space.setPlaces(Integer.valueOf(this.places));
        return space;
    }

    @SneakyThrows
    public Space asDTO() {
        Space space = new Space();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        space.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        space.setRoomName(this.roomName);
        Date date = sdf.parse(this.getRegistrationDate());
        space.setRegistrationDate(date);
        space.setPlaces(Integer.valueOf(this.places));
        return space;
    }
}
