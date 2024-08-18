package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.RfidRequest;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class RegistryData {
    private String id;
    private String codeRfid;
    private String date;
    private String hour;
    private String statusRfid;

    public  static RegistryData from(RfidRequest rfidRequest){
        RegistryData  registryData = new RegistryData();
        SimpleDateFormat outputFormatter = new SimpleDateFormat("HH:mm");
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        registryData.setId(String.valueOf(rfidRequest.getId()));
        registryData.setCodeRfid(rfidRequest.getCodeRfid());
        registryData.setDate(outputFormatterDate.format(rfidRequest.getDate()));
        registryData.setHour(outputFormatter.format(rfidRequest.getHour()));
        registryData.setStatusRfid(rfidRequest.getStatusRfid().toString());
        return registryData;
    }
}