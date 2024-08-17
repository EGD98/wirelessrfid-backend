package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.CatUserType;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Client;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Corporation;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
import com.microcontrollersystem.wirelessrfidbackend.repositories.CatUserTypeRepository;
import com.microcontrollersystem.wirelessrfidbackend.repositories.ClientRepository;
import com.microcontrollersystem.wirelessrfidbackend.repositories.CorporationRepository;
import com.microcontrollersystem.wirelessrfidbackend.repositories.SpaceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class CatalogService {

    private final CatUserTypeRepository catUserTypeRepository;
    private final CorporationRepository corporationRepository;
    private final SpaceRepository spaceRepository;
    private final ClientRepository clientRepository;

    public List<Map<String, Object>> getCatalog(String name) {
        List<Map<String, Object>> catalogList = new ArrayList<>();
        switch (name) {
            case "USER_TYPE":
                List<CatUserType> catUserTypeList = catUserTypeRepository.findAllByStatusTrue();
                for (CatUserType catUserType : catUserTypeList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", catUserType.getId());
                    map.put("description", catUserType.getTypeUser());
                    catalogList.add(map);
                }
                return catalogList;
            case "CORPORATION":
                List<Corporation> corporationList = corporationRepository.findAllByStatusTrue();
                for (Corporation corporation : corporationList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", corporation.getId());
                    map.put("description", corporation.getName());
                    catalogList.add(map);
                }
                return catalogList;
            case "SPACE_TYPE":
                List<Space> spaceList = spaceRepository.findAllByStatusTrue();
                for (Space space : spaceList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", space.getId());
                    map.put("description", space.getRoomName());
                    catalogList.add(map);
                }
                return catalogList;
            case "CLIENT_TYPE":
                List<Client> clientList = clientRepository.findAllByStatusTrue();
                for (Client client : clientList) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", client.getId());
                    map.put("description", client.getName()+" "+ client.getFirstName() +" "+client.getLastName());
                    catalogList.add(map);

                }
                return catalogList;
            default:
                return new ArrayList<>();
        }

    }
}
