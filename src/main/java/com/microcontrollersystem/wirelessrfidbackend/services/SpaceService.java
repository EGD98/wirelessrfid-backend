package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.dto.SpaceData;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
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
public class SpaceService {
    private final SpaceRepository spaceRepository;

    public List<Space> getSpaceList() {
        return spaceRepository.findAllByStatusTrue();
    }

    public String addSpace(SpaceData spaceData, Integer idUser) {
        Space space;
        if (!spaceData.getId().isEmpty()) {
            space = spaceRepository.findById(Integer.parseInt(spaceData.getId())).orElseThrow(() -> new RuntimeException("Espacio no existe"));
            space = spaceData.asDTO(space);
        } else {
            space = spaceData.asDTO();
        }
        if (space.getId() != null) {
            space.setEditDate(new Date());
            space.setEditorUser(idUser);
        } else {
            space.setCreatorUser(idUser);
        }
        Space spaceSaved = spaceRepository.save(space);
        String respuesta = space.getId() != null ? "Espacio actualizado con exito" : "Espacio creado con exito";
        return Objects.nonNull(spaceSaved.getId()) ? respuesta
                : "Ha ocurrido un error al intentar crear el Espacio";
    }

    public String deleteSpace(Integer idUserEditor, Integer idSpace) {
        Space space = spaceRepository.findById(idSpace).orElseThrow(() -> new RuntimeException("Espacio no existe"));
        space.setStatus(Boolean.FALSE);
        space.setEditorUser(idUserEditor);
        space.setEditDate(new Date());
        Space spaceSaved = spaceRepository.save(space);
        String respuesta = "Espacio eliminado con exito" ;
        return Objects.nonNull(spaceSaved.getId()) ? respuesta
                : "Ha ocurrido un error al intentar eliminar el Espacio";
    }

}
