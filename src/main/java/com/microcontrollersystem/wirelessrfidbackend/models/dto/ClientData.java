package com.microcontrollersystem.wirelessrfidbackend.models.dto;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ClientData {
    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String  phoneNumber;
    private String email;

    public  static ClientData from(Client client){
        ClientData clientData = new ClientData();
        SimpleDateFormat outputFormatter = new SimpleDateFormat("HH:mm");
        SimpleDateFormat outputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
        clientData.setId(client.getId().toString());
        clientData.setName(client.getName());
        clientData.setFirstName(client.getFirstName());
        clientData.setLastName(client.getLastName());
        clientData.setPhoneNumber(client.getPhoneNumber().toString());
        clientData.setEmail(client.getEmail());
        return clientData;
    }

    @SneakyThrows
    public Client asDTO() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Client client = new Client();
        client.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        client.setName(this.name);
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setPhoneNumber(Long.valueOf(this.phoneNumber));
        client.setEmail(this.email);
        return client;
    }
    @SneakyThrows
    public Client asDTO(Client client) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        client.setId(this.id.isEmpty() ? null : Integer.parseInt(this.id));
        client.setName(this.name);
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setPhoneNumber(Long.valueOf(this.phoneNumber));
        client.setEmail(this.email);
        return client;
    }

}
