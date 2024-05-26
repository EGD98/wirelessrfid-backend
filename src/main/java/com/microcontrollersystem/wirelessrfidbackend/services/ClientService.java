package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.ClientData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getClientList() {
        return clientRepository.findAllByStatusTrue();
    }

    public String addClient(ClientData clientData, Integer idUser) {
        Client client;
        if(!clientData.getId().isEmpty()){
            client = clientRepository.findById(Integer.parseInt(clientData.getId())).orElseThrow(() -> new RuntimeException("Cliente no existe"));
            client = clientData.asDTO(client);
        } else {
            client = clientData.asDTO();
        }
        if (client.getId() != null) {
            client.setEditDate(new Date());
            client.setEditorUser(idUser);
        } else {
            client.setCreatorUser(idUser);
        }
        Client clientSaved = clientRepository.save(client);
        String respuesta = client.getId() != null ? "Cliente actualizado con exito" : "Cliente creado con exito";
        return Objects.nonNull(clientSaved.getId()) ? respuesta
                : "Ha ocurrido un error al intentar crear el cliente";
    }

    public String deleteClient(Integer idUserEditor, Integer idUser) {
        Client client = clientRepository.findById(idUser).orElseThrow(() -> new RuntimeException("Cliente no existe"));
        client.setStatus(Boolean.FALSE);
        client.setEditorUser(idUserEditor);
        client.setEditDate(new Date());
        Client clientSaved = clientRepository.save(client);
        String respuesta = "Cliente eliminado con exito" ;
        return Objects.nonNull(clientSaved.getId()) ? respuesta
                : "Ha ocurrido un error al intentar eliminar el cliente";
    }

}
