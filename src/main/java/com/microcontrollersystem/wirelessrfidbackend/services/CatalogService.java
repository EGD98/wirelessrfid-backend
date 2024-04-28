package com.microcontrollersystem.wirelessrfidbackend.services;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.CatUserType;
import com.microcontrollersystem.wirelessrfidbackend.models.orm.Corporation;
import com.microcontrollersystem.wirelessrfidbackend.repositories.CatUserTypeRepository;
import com.microcontrollersystem.wirelessrfidbackend.repositories.CorporationRepository;
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
            default:
                return new ArrayList<>();
        }

    }
}
